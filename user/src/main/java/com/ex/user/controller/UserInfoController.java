package com.ex.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ex.model.entity.user.UserInfo;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.vo.UserInfoVO;
import com.ex.user.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务控制器
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:23
 */
@Slf4j
@RestController
@RequestMapping("/user/api/{device}/{version}/")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @PutMapping("/info")
    @ApiOperation(value = "获取用户信息")
    public ResultVO getUser() {
        Long userId = getUid();
        UserInfo userInfo =
                userInfoService.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUserId, userId));
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo, userInfoVO);
        return Result.success(userInfoVO);
    }
}