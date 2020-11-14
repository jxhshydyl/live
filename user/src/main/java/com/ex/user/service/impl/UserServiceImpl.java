package com.ex.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
import com.ex.user.service.UserService;
import com.ex.user.util.ShareCodeUtil;
import com.ex.util.encrypt.EncryptUtil;
import com.yibu.dex.rpc.message.MessageFeign;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public ResultVO sendMessage(boolean isLogin, MessageDTO messageDTO) {
        //登录发送短信
        Long userId = null;
        String userName = null;
        String receiveAddress = messageDTO.getReceiveAddress();
        String countryCode = messageDTO.getCountryCode();
        EnumMessageBusinessType msgType = EnumMessageBusinessType.getMsgType(messageDTO.getBusinessType());
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
            //注册验证码不需要判断是否有该用户
            if (msgType.getId() != EnumMessageBusinessType.REGISTER.getId()) {
                User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                        .eq(User::getMobile, receiveAddress)
                        .or()
                        .eq(User::getEmail, receiveAddress));
                if (user == null) {
                    return Result.error(ResultEnum.USER_NOT);
                }
            }
        }
        String code = "";
        Message message = new Message();
        message.setUserId(userId);
        message.setUserName(userName);
        message.setContext("");
        message.setReceiveAddress(receiveAddress);
        message.setCountryCode(countryCode);
        message.setType(EnumMessageType.手机短信.getCode());
        ResultVO resultVO = messageFeign.sendMessage(message);
        if (resultVO.isSuccess()) {
            redisTemplate.opsForValue().set(RedisKeyConstant.MESSAGE, code, RedisTimeConstant.MESSAGE_EXPIRE, TimeUnit.MINUTES);
        }
        return messageFeign.sendMessage(message);
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

        user = new User();
        user.setUserName(userDTO.getUserName());
        user.setNickName(userDTO.getNickName());
        user.setHeadPortrait(userDTO.getHeadPortrait());
        user.setLocation(userDTO.getLocation());
        user.setSex(userDTO.getSex());
        user.setRecommendId(recommendId);
        userMapper.insert(user);
        user.setRecommendCode(ShareCodeUtil.idToCode(user.getId()));
        String password = EncryptUtil.SHA256(EncryptUtil.MD5(String.valueOf(user.getId())) + EncryptUtil.SHA(user.getLoginPwd()));
        user.setLoginPwd(password);
        userMapper.updateById(user);
        return Result.success();
    }
}