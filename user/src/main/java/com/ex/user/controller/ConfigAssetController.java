package com.ex.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ex.model.entity.base.ConfigAsset;
import com.ex.model.enums.EnumEither;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.vo.ConfigAssetVO;
import com.ex.user.service.ConfigAssetService;
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
 * 资产配置服务控制器
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-19 12:02:05
 */
@Slf4j
@RestController
@RequestMapping("/user/api/{device}/{version}")
@Api(value = "asset", tags = "配置-资产")
public class ConfigAssetController {

    @Autowired
    private ConfigAssetService configAssetService;

    @GetMapping("/asset/config")
    @ApiOperation(value = "获取资产配置信息")
    public ResultVO getTaskConfig() {
        List<ConfigAsset> list = configAssetService.list(new LambdaQueryWrapper<ConfigAsset>()
                .eq(ConfigAsset::getStatus, EnumEither.EFFECTIVE.getCode())
                .eq(ConfigAsset::getDisplay, EnumEither.YES.getCode())
                .eq(ConfigAsset::getIsDelete, EnumEither.NOT_DELETE.getCode()));
        List<ConfigAssetVO> configAssetVOS = new ArrayList<>();
        list.forEach(configAsset -> {
            ConfigAssetVO configAssetVO = new ConfigAssetVO();
            BeanUtils.copyProperties(configAsset, configAssetVO);
            configAssetVOS.add(configAssetVO);
        });
        return Result.success(configAssetVOS);
    }

}