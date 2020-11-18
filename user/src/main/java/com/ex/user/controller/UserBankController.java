package com.ex.user.controller;

import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.UserBankBindDTO;
import com.ex.user.service.UserBankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 服务控制器
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:22
 */
@Slf4j
@RestController
@RequestMapping("user/api/{device}/{version}")
@Api(value = "bank", tags = "绑定银行卡")
public class UserBankController extends BaseController {

    @Autowired
    private UserBankService userBankService;

    @GetMapping("/bank")
    @ApiOperation(value = "获取银行卡")
    public ResultVO getBank() {
        return userBankService.getBank(getUid());
    }

    @PostMapping("/bank")
    @ApiOperation(value = "绑定银行卡")
    public ResultVO bingBank(@RequestBody @Validated UserBankBindDTO userBankBindDTO) {
        userBankBindDTO.setUserId(getUid());
        return userBankService.bingBank(userBankBindDTO);
    }

}