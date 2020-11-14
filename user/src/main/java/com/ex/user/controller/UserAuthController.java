package com.ex.user.controller;

import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.UserAuthDTO;
import com.ex.user.service.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("user/api/{device}/{version}")
public class UserAuthController extends BaseController{

    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/auth")
    public ResultVO submitAuth(@RequestBody @Validated UserAuthDTO userAuthDTO) {
        userAuthDTO.setUserId(getUid());
        return userAuthService.submitAuth(userAuthDTO);
    }
}