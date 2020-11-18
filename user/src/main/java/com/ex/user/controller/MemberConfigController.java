package com.ex.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ex.model.entity.base.MemberConfig;
import com.ex.model.enums.EnumEither;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.enums.EnumMemberType;
import com.ex.user.model.vo.MemberConfigVO;
import com.ex.user.service.MemberConfigService;
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
 * 会员类型配置服务控制器
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-15 19:57:41
 */
@Slf4j
@RestController
@RequestMapping("/user/api/{device}/{version}")
@Api(value = "member", tags = "会员配置信息")
public class MemberConfigController {

    @Autowired
    public MemberConfigService memberConfigService;

    @GetMapping("/member/config")
    @ApiOperation(value = "获取会员类型信息")
    public ResultVO getMemberConfig() {
        List<MemberConfig> list = memberConfigService.list(new LambdaQueryWrapper<MemberConfig>()
                .eq(MemberConfig::getStatus, EnumEither.EFFECTIVE.getCode()));
        List<MemberConfigVO> memberConfigVOS = new ArrayList<>();
        list.forEach(memberConfig -> {
            MemberConfigVO memberConfigVO = new MemberConfigVO();
            BeanUtils.copyProperties(memberConfig, memberConfigVO);
            EnumMemberType type = EnumMemberType.getType(memberConfigVO.getType());
            if (type != null) {
                memberConfigVO.setTypeName(type.getName());
            }
            memberConfigVOS.add(memberConfigVO);
        });
        return Result.success(memberConfigVOS);
    }
}