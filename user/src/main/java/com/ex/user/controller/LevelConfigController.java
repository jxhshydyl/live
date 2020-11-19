package com.ex.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ex.model.entity.base.AuthConfig;
import com.ex.model.entity.base.LevelConfig;
import com.ex.model.enums.EnumEither;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.vo.AuthConfigVO;
import com.ex.user.model.vo.LevelConfigVO;
import com.ex.user.service.AuthConfigService;
import com.ex.user.service.LevelConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 等级配置服务控制器
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-15 19:57:41
 */
@Slf4j
@RestController
@RequestMapping("/user/api/{device}/{version}")
@Api(value = "level", tags = "配置-等级权限")
public class LevelConfigController {

    @Autowired
    private LevelConfigService levelConfigService;

    @Autowired
    private AuthConfigService authConfigService;

    @GetMapping("/level/config")
    @ApiOperation(value = "获取等级权限配置信息")
    public ResultVO getUserMember() {
        List<LevelConfig> list = levelConfigService.list(new LambdaQueryWrapper<LevelConfig>()
                .eq(LevelConfig::getStatus, EnumEither.EFFECTIVE.getCode()));
        List<LevelConfigVO> levelConfigVOS = new ArrayList<>();
        list.forEach(levelConfig -> {
            Integer level = levelConfig.getLevel();
            LevelConfigVO levelConfigVO = new LevelConfigVO();
            BeanUtils.copyProperties(levelConfig, levelConfigVO);
            List<AuthConfig> authConfigs = authConfigService.list(new LambdaQueryWrapper<AuthConfig>()
                    .eq(AuthConfig::getStatus, EnumEither.EFFECTIVE.getCode())
                    .le(AuthConfig::getLevel, level));
            List<AuthConfigVO> authConfigVOS = new ArrayList<>();
            authConfigs.forEach(authConfig -> {
                AuthConfigVO authConfigVO = new AuthConfigVO();
                BeanUtils.copyProperties(authConfig, authConfigVO);
                authConfigVOS.add(authConfigVO);
            });
            levelConfigVO.setAuth(authConfigVOS);
            levelConfigVOS.add(levelConfigVO);
        });
        return Result.success(levelConfigVOS);
    }
}