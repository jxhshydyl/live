package com.ex.user.controller;

import com.ex.model.entity.message.Message;
import com.ex.model.entity.user.User;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.MessageDTO;
import com.ex.user.model.dto.UserDTO;
import com.ex.user.model.vo.UserVO;
import com.ex.user.service.UserService;
import com.yibu.dex.rpc.message.MessageFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务控制器
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:22
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/send/message")
    public ResultVO sendMessage(MessageDTO messageDTO) {
        return userService.sendMessage(messageDTO);
    }

    @PostMapping("/register")
    public ResultVO register(UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @PostMapping("/login")
    public ResultVO login() {
        return null;
    }

    @GetMapping
    public ResultVO getUser() {
        //todo 从 head 中获取userId
        Long userId = null;
        User user = userService.getById(userId);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return Result.success(userVO);
    }

}