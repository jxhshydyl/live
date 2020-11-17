package com.ex.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ex.model.entity.user.UserInfo;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.UserInfoDTO;
import com.ex.user.model.vo.UserInfoVO;
import com.ex.user.service.UserInfoService;
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
 * @since 2020-11-11 18:24:23
 */
@Slf4j
@RestController
@RequestMapping("/user/api/{device}/{version}")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/info")
    @ApiOperation(value = "获取用户信息")
    public ResultVO getUser(@RequestParam(value = "id", required = false) Long userId) {
        if (userId == null || userId == 0L) {
            userId = getUid();
        }
        UserInfo userInfo = userInfoService.getOne(new LambdaQueryWrapper<UserInfo>()
                        .eq(UserInfo::getUserId, userId));
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo, userInfoVO);
        return Result.success(userInfoVO);
    }

    @PutMapping("/info")
    @ApiOperation(value = "更新用户信息")
    public ResultVO updateUserInfo(@RequestBody @Validated UserInfoDTO userInfoDTO) {
        userInfoDTO.setUserId(getUid());
        return userInfoService.updateUserInfo(userInfoDTO);
    }
}