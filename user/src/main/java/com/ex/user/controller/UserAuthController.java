package com.ex.user.controller;

import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.UserAuthDTO;
import com.ex.user.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Api(value = "auth", tags = "用户实名认证")
public class UserAuthController extends BaseController{

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/auth")
    @ApiOperation(value = "获取实名认证")
    public ResultVO getAuth() {
        return userAuthService.getAuth(getUid());
    }

    @PostMapping("/auth")
    @ApiOperation(value = "实名认证")
    public ResultVO submitAuth(@RequestBody @Validated UserAuthDTO userAuthDTO) {
        userAuthDTO.setUserId(getUid());
        return userAuthService.submitAuth(userAuthDTO);
    }
}