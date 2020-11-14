package com.ex.user.controller;

import com.ex.model.entity.user.User;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.MessageDTO;
import com.ex.user.model.dto.UserDTO;
import com.ex.user.model.dto.UserLoginDTO;
import com.ex.user.model.vo.UserVO;
import com.ex.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 服务控制器
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:22
 */
@Slf4j
@RestController
@RequestMapping("user/api/{device}/{version}")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("/send/message")
    public ResultVO sendMessage(MessageDTO messageDTO) {
        boolean isLogin = false;
        return userService.sendMessage(isLogin, messageDTO);
    }

    @PostMapping("/register")
    public ResultVO register(UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @PostMapping("/login")
    public ResultVO loginByPassWord(@RequestBody @Validated UserLoginDTO userLoginDTO) {
        return userService.loginByPassWord(userLoginDTO);
    }

    @PostMapping("/login/code")
    public ResultVO loginByCode(@RequestBody @Validated UserLoginDTO userLoginDTO) {
        return userService.loginByCode(userLoginDTO);
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