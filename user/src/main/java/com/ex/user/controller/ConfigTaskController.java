package com.ex.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ex.model.entity.base.ConfigTask;
import com.ex.model.enums.EnumEither;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.vo.ConfigTaskVO;
import com.ex.user.service.ConfigTaskService;
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
 * 任务配置服务控制器
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-18 23:25:42
 */
@Slf4j
@RestController
@RequestMapping("/user/api/{device}/{version}")
@Api(value = "task", tags = "配置-任务")
public class ConfigTaskController {

    @Autowired
    private ConfigTaskService configTaskService;

    @GetMapping("/task/config")
    @ApiOperation(value = "获取任务配置信息")
    public ResultVO getTaskConfig() {
        List<ConfigTask> list = configTaskService.list(new LambdaQueryWrapper<ConfigTask>()
                .eq(ConfigTask::getStatus, EnumEither.EFFECTIVE.getCode()));
        List<ConfigTaskVO> configTaskVOS = new ArrayList<>();
        list.forEach(configTask -> {
            ConfigTaskVO configTaskVO = new ConfigTaskVO();
            BeanUtils.copyProperties(configTask, configTaskVO);
            configTaskVOS.add(configTaskVO);
        });
        return Result.success(configTaskVOS);
    }

}