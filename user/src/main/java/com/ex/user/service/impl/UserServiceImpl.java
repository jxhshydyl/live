package com.ex.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ex.model.entity.message.Message;
import com.ex.model.entity.user.User;
import com.ex.model.enums.ResultEnum;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.mapper.UserMapper;
import com.ex.user.model.dto.MessageDTO;
import com.ex.user.model.dto.UserDTO;
import com.ex.user.service.UserService;
import com.yibu.dex.rpc.message.MessageFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public ResultVO sendMessage(boolean isLogin, MessageDTO messageDTO) {
        //登录发送短信
        if (isLogin) {

            //未登录发送短信
        } else {

        }
        Message message = new Message();
        return messageFeign.sendMessage(message);
    }

    @Override
    public ResultVO register(UserDTO userDTO) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, userDTO.getUserName()));
        if (user != null) {
            return Result.error(ResultEnum.USER_NAME_EXISTS);
        }
        //todo 校验验证码
        user = new User();
        user.setUserName(userDTO.getUserName());
        user.setNickName(userDTO.getNickName());
        user.setHeadPortrait(userDTO.getHeadPortrait());
        user.setLocation(userDTO.getLocation());
        user.setSex(userDTO.getSex());
        userMapper.insert(user);
        return Result.success();
    }
}