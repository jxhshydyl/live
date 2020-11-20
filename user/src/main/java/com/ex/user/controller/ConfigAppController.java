package com.ex.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ex.model.entity.user.ConfigApp;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.vo.ConfigAppVO;
import com.ex.user.service.ConfigAppService;
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
 * app版本更新配置服务控制器
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-20 17:29:14
 */
@Slf4j
@RestController
@RequestMapping("/user/api/{device}/{version}")
@Api(value = "gift", tags = "配置-更新")
public class ConfigAppController {

    @Autowired
    private ConfigAppService configAppService;

    @GetMapping("/app")
    @ApiOperation(value = "获取版本更新信息")
    public ResultVO getImgAd() {
        List<ConfigApp> configImgAds = configAppService.list(new LambdaQueryWrapper<ConfigApp>());
        List<ConfigAppVO> configAppVOS = new ArrayList<>();
        configImgAds.forEach(configApp -> {
            ConfigAppVO configAppVO = new ConfigAppVO();
            BeanUtils.copyProperties(configApp, configAppVO);
            configAppVOS.add(configAppVO);
        });
        return Result.success(configAppVOS);
    }
}