package com.ex.user.controller;

import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.UserBankBindDTO;
import com.ex.user.service.UserBankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务控制器
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:22
 */
@Slf4j
@RestController
@RequestMapping("user/api/{device}/{version}")
public class UserBankController extends BaseController {

    @Autowired
    private UserBankService userBankService;

    @PostMapping("/bank")
    public ResultVO bingBank(UserBankBindDTO userBankBindDTO) {
        userBankBindDTO.setUserId(getUid());
        return userBankService.bingBank(userBankBindDTO);
    }

}