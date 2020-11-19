package com.ex.asset.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ex.asset.model.vo.UserAccountVO;
import com.ex.asset.service.UserAccountService;
import com.ex.model.entity.asset.UserAccount;
import com.ex.model.entity.user.User;
import com.ex.model.entity.user.UserInfo;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
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
 * 用户账户服务控制器
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-19 16:11:11
 */
@Slf4j
@RestController
@RequestMapping("/asset/api/{device}/{version}")
public class UserAccountController extends BaseController {

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/account")
    @ApiOperation(value = "获取用户账户信息")
    public ResultVO getUser() {
        Long userId = getUid();
        List<UserAccount> list = userAccountService.list(new LambdaQueryWrapper<UserAccount>().eq(UserAccount::getUserId, userId));
        List<UserAccountVO> userAccountVOS = new ArrayList<>();
        list.forEach(userAccount -> {
            UserAccountVO userAccountVO = new UserAccountVO();
            BeanUtils.copyProperties(userAccount, userAccountVO);
            userAccountVOS.add(userAccountVO);
        });
        return Result.success(userAccountVOS);
    }

}