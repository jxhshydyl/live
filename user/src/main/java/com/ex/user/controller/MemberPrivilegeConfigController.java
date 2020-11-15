package com.ex.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ex.model.entity.base.MemberPrivilegeConfig;
import com.ex.model.enums.EnumEither;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.service.MemberPrivilegeConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 会员特权配置服务控制器
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-15 19:57:41
 */
@Slf4j
@RestController
@RequestMapping("/user/api/{device}/{version}")
public class MemberPrivilegeConfigController {

    @Autowired
    public MemberPrivilegeConfigService memberPrivilegeConfigService;

    @GetMapping("/member/privilege")
    public ResultVO getUserMember() {
        List<MemberPrivilegeConfig> list = memberPrivilegeConfigService.list(new LambdaQueryWrapper<MemberPrivilegeConfig>()
                .eq(MemberPrivilegeConfig::getStatus, EnumEither.EFFECTIVE.getCode()));
        return Result.success(list);
    }
}