package com.ex.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ex.model.entity.base.MemberPrivilegeConfig;
import com.ex.model.enums.EnumEither;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.vo.MemberConfigVO;
import com.ex.user.model.vo.MemberPrivilegeConfigVO;
import com.ex.user.service.MemberPrivilegeConfigService;
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
 * 会员特权配置服务控制器
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-15 19:57:41
 */
@Slf4j
@RestController
@RequestMapping("/user/api/{device}/{version}")
@Api(value = "privilege", tags = "配置-会员特权")
public class MemberPrivilegeConfigController {

    @Autowired
    public MemberPrivilegeConfigService memberPrivilegeConfigService;

    @GetMapping("/member/privilege")
    @ApiOperation(value = "获取会员特权配置信息")
    public ResultVO getUserMember() {
        List<MemberPrivilegeConfig> list = memberPrivilegeConfigService.list(new LambdaQueryWrapper<MemberPrivilegeConfig>()
                .eq(MemberPrivilegeConfig::getStatus, EnumEither.EFFECTIVE.getCode())
                .orderByDesc(MemberPrivilegeConfig::getSort));
        List<MemberPrivilegeConfigVO> privilegeConfigVOS = new ArrayList<>();
        list.forEach(memberPrivilegeConfig -> {
            MemberPrivilegeConfigVO privilegeConfigVO = new MemberPrivilegeConfigVO();
            BeanUtils.copyProperties(memberPrivilegeConfig, privilegeConfigVO);
            privilegeConfigVOS.add(privilegeConfigVO);
        });
        return Result.success(privilegeConfigVOS);
    }
}