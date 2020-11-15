package com.ex.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ex.model.constant.Constants;
import com.ex.model.constant.RedisKeyConstant;
import com.ex.model.constant.RedisTimeConstant;
import com.ex.model.entity.message.Message;
import com.ex.model.entity.user.User;
import com.ex.model.enums.ResultEnum;
import com.ex.model.enums.message.EnumMessageBusinessType;
import com.ex.model.enums.message.EnumMessageType;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.mapper.UserMapper;
import com.ex.user.model.dto.MessageDTO;
import com.ex.user.model.dto.UserDTO;
import com.ex.user.model.dto.UserLoginDTO;
import com.ex.user.model.vo.SessionUser;
import com.ex.user.service.UserService;
import com.ex.user.util.JWTUtils;
import com.ex.user.util.MessageUtil;
import com.ex.user.util.ShareCodeUtil;
import com.ex.util.encrypt.EncryptUtil;
import com.ex.rpc.message.MessageFeign;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 服务接口实现
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:22
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private MessageFeign messageFeign;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MessageUtil messageUtil;

    @Override
    public ResultVO sendMessage(boolean isLogin, MessageDTO messageDTO) {
        //登录发送短信
        Long userId = null;
        String userName = null;
        String receiveAddress = messageDTO.getReceiveAddress();
        String countryCode = messageDTO.getCountryCode();
        Integer businessType = messageDTO.getBusinessType();
        EnumMessageBusinessType msgType = EnumMessageBusinessType.getMsgType(businessType);
        if (msgType == null) {
            return Result.error(ResultEnum.MESSAGE_TYPE_ERROR);
        }
        if (isLogin) {
            //未登录发送短信
            User user = userMapper.selectById(userId);
            userName = user.getUserName();
            receiveAddress = user.getMobile();
            countryCode = user.getCountryCode();
        } else {
            //注册验证码和更改手机新手机验证码 需要判断是否有该用户
            if (businessType == EnumMessageBusinessType.REGISTER.getId()
                    || businessType == EnumMessageBusinessType.UPDATE_MOBILE_NEW_MOBILE.getId()) {
                User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                        .eq(User::getMobile, receiveAddress)
                        .or()
                        .eq(User::getEmail, receiveAddress));
                if (user != null) {
                    return Result.error(ResultEnum.MOBILE_USED);
                }
            }
        }
        String code = String.valueOf(new Random().nextInt(899999) + 100000);
        Message message = new Message();
        message.setUserId(userId);
        message.setUserName(userName);
        //后面改成短信模板
        message.setContext(msgType.getName() + ":" + code);
        message.setReceiveAddress(receiveAddress);
        message.setCountryCode(countryCode);
        message.setType(EnumMessageType.手机短信.getCode());
        try {
            ResultVO resultVO = messageUtil.sendMessage(msgType, receiveAddress, code);
            if (resultVO.isSuccess()) {
                return messageFeign.sendMessage(message);
            }
        } catch (Exception e) {
            log.error("发送短信异常：", e);
        }
        return Result.error(ResultEnum.MESSAGE_SEND_FAIL);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO register(UserDTO userDTO) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, userDTO.getUserName()));
        if (user != null) {
            return Result.error(ResultEnum.USER_NAME_EXISTS);
        }
        String recommendCode = userDTO.getRecommendCode();
        Long recommendId = null;
        if (StringUtils.isNotBlank(recommendCode)) {
            User recommendUser = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getRecommendCode, recommendCode));
            if (recommendUser == null) {
                return Result.error(ResultEnum.USER_NAME_EXISTS);
            }
            recommendId = recommendUser.getId();
        }
        // TODO: 2020/11/14   校验短信验证码
        String code = userDTO.getCode();
        String mobile = userDTO.getMobile();
        ResultVO resultVO = messageUtil.checkMessage(EnumMessageBusinessType.REGISTER, mobile, code);
        if (resultVO.isSuccess()) {
            user = new User();
            user.setUserName(userDTO.getUserName());
            user.setRecommendId(recommendId);
            userMapper.insert(user);
            user.setRecommendCode(ShareCodeUtil.idToCode(user.getId()));
            String password = EncryptUtil.SHA256(EncryptUtil.MD5(String.valueOf(user.getId())) + EncryptUtil.SHA(user.getLoginPwd()));
            user.setLoginPwd(password);
            userMapper.updateById(user);
            return Result.success();
        }
        return resultVO;
    }

    @Override
    public ResultVO loginByPassWord(UserLoginDTO userLoginDTO) {
        String userName = userLoginDTO.getUserName();
        Long count = (Long) redisTemplate.opsForValue().get(RedisKeyConstant.LOGIN_PASSWORD_ERROR + userName);
        if (count != null && count >= 5) {
            return Result.error(ResultEnum.USER_PASSWORD_INPUT_ERROR);
        }
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, userName));
        if (user == null) {
            return Result.error(ResultEnum.USER_NOT);
        }
        Long userId = user.getId();
        String loginPwd = user.getLoginPwd();
        String password = EncryptUtil.SHA256(EncryptUtil.MD5(String.valueOf(userId)) + EncryptUtil.SHA(userLoginDTO.getPassword()));
        //限制密码错误次数
        if (!password.equals(loginPwd)) {
            if (redisTemplate.hasKey(RedisKeyConstant.LOGIN_PASSWORD_ERROR + userName)) {
                redisTemplate.opsForValue().increment(RedisKeyConstant.LOGIN_PASSWORD_ERROR + userName);
            } else {
                redisTemplate.opsForValue().set(RedisKeyConstant.LOGIN_PASSWORD_ERROR + userName, 1, RedisTimeConstant.LOGIN_PASSWORD_ERROR_LOCK, TimeUnit.HOURS);
            }
        }
        redisTemplate.delete(RedisKeyConstant.LOGIN_PASSWORD_ERROR + userName);
        return login(user);
    }

    @Override
    public ResultVO loginByCode(UserLoginDTO userLoginDTO) {
        String userName = userLoginDTO.getUserName();
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, userName));
        if (user == null) {
            return Result.error(ResultEnum.USER_NOT);
        }
        ResultVO resultVO = messageUtil.checkMessage(EnumMessageBusinessType.REGISTER, userName, userLoginDTO.getPassword());
        if (resultVO.isSuccess()) {
            return login(user);
        }
        return resultVO;
    }

    private ResultVO login(User user) {
        Long userId = user.getId();
        String userName = user.getUserName();
        String jwtToken = JWTUtils.createJwtToken(userId, user.getUserName());
        SessionUser sessionUser = new SessionUser();
        sessionUser.setUserId(userId);
        sessionUser.setUserName(userName);
        sessionUser.setToken(jwtToken);
        sessionUser.setLoginTime(LocalDateTime.now());
        redisTemplate.opsForValue().set(RedisKeyConstant.SSO_TOKEN + userId, jwtToken, Constants.JWT_LOGIN_TIME, TimeUnit.MILLISECONDS);
        redisTemplate.opsForValue().set(RedisKeyConstant.SSO_SESSION + jwtToken, sessionUser, Constants.JWT_LOGIN_TIME, TimeUnit.MILLISECONDS);
        return Result.success(sessionUser);
    }

    @Override
    public Integer updateUserById(User user) {
        return userMapper.updateUserById(user);
    }
}