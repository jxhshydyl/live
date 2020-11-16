package com.ex.user.controller;

import com.ex.model.entity.user.User;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.MessageDTO;
import com.ex.user.model.dto.UserDTO;
import com.ex.user.model.dto.UserLoginCodeDTO;
import com.ex.user.model.dto.UserLoginDTO;
import com.ex.user.model.vo.UserVO;
import com.ex.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/user/api/{device}/{version}")
@Api(value = "user", tags = "用户个人信息")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResultVO getUser() {
        Long userId = getUid();
        User user = userService.getById(userId);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return Result.success(userVO);
    }

    @PostMapping("/send/message")
    @ApiOperation(value = "发送短信")
    public ResultVO sendMessage(@RequestBody @Validated MessageDTO messageDTO) {
        boolean isLogin = false;
        return userService.sendMessage(isLogin, messageDTO);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public ResultVO register(UserDTO userDTO) {
        return userService.register(userDTO);
    }

    @PostMapping("/login")
    @ApiOperation(value = "密码登录")
    public ResultVO loginByPassWord(@RequestBody @Validated UserLoginDTO userLoginDTO) {
        return userService.loginByPassWord(userLoginDTO);
    }

    @PostMapping("/login/code")
    @ApiOperation(value = "短信验证码登录")
    public ResultVO loginByCode(@RequestBody @Validated UserLoginCodeDTO userLoginCodeDTO) {
        return userService.loginByCode(userLoginCodeDTO);
    }

}