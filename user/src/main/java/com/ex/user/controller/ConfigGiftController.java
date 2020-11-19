package com.ex.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ex.model.entity.base.ConfigGift;
import com.ex.model.enums.EnumEither;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.vo.ConfigGiftVO;
import com.ex.user.service.ConfigGiftService;
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
 * 礼物配置服务控制器
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-19 12:02:06
 */
@Slf4j
@RestController
@RequestMapping("/user/api/{device}/{version}")
@Api(value = "gift", tags = "配置-礼物")
public class ConfigGiftController {

    @Autowired
    private ConfigGiftService configGiftService;

    @GetMapping("/gift/config")
    @ApiOperation(value = "获取礼物配置信息")
    public ResultVO getTaskConfig() {
        List<ConfigGift> list = configGiftService.list(new LambdaQueryWrapper<ConfigGift>()
                .eq(ConfigGift::getStatus, EnumEither.EFFECTIVE.getCode())
                .eq(ConfigGift::getDisplay, EnumEither.YES.getCode())
                .eq(ConfigGift::getIsDelete, EnumEither.NOT_DELETE.getCode()));
        List<ConfigGiftVO> configGiftVOS = new ArrayList<>();
        list.forEach(configGift -> {
            ConfigGiftVO configGiftVO = new ConfigGiftVO();
            BeanUtils.copyProperties(configGift, configGiftVO);
            configGiftVOS.add(configGiftVO);
        });
        return Result.success(configGiftVOS);
    }

}