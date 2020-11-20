package com.ex.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ex.model.entity.user.ConfigImgAd;
import com.ex.model.enums.EnumEither;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.vo.ConfigImgAdVO;
import com.ex.user.service.ConfigImgAdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 轮播图配置服务控制器
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-20 17:29:15
 */
@Slf4j
@RestController
@RequestMapping("/user/api/{device}/{version}")
@Api(value = "img", tags = "配置-轮播图")
public class ConfigImgAdController {

    @Autowired
    private ConfigImgAdService configImgAdService;

    @GetMapping("/ad")
    @ApiOperation(value = "获取轮播图配置信息")
    public ResultVO getImgAd(@RequestParam(required = false, defaultValue = "cn") String lan,
                             @RequestParam(required = false, defaultValue = "0") Integer type) {
        LambdaQueryWrapper<ConfigImgAd> queryWrapper = new LambdaQueryWrapper<ConfigImgAd>()
                .eq(ConfigImgAd::getLanguage, lan)
                .eq(ConfigImgAd::getCategoryType, type)
                .eq(ConfigImgAd::getIsRecommend, EnumEither.YES.getCode())
                .eq(ConfigImgAd::getIsDeleted, EnumEither.NOT_DELETE.getCode())
                .orderByAsc(ConfigImgAd::getOrderBy);
        List<ConfigImgAd> configImgAds = configImgAdService.list(queryWrapper);
        List<ConfigImgAdVO> configImgAdVOS = new ArrayList<>();
        configImgAds.forEach(configImgAd -> {
            ConfigImgAdVO configImgAdVO = new ConfigImgAdVO();
            BeanUtils.copyProperties(configImgAd, configImgAdVO);
            configImgAdVOS.add(configImgAdVO);
        });
        return Result.success(configImgAdVOS);
    }
}