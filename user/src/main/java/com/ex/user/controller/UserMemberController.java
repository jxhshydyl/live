package com.ex.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ex.model.entity.user.UserMember;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.service.UserMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务控制器
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-15 19:48:00
 */
@Slf4j
@RestController
@RequestMapping("/user/api/{device}/{version}")
public class UserMemberController extends BaseController {

    @Autowired
    public UserMemberService userMemberService;

    @GetMapping("/member")
    public ResultVO getUserMember() {
        Long userId = getUid();
        UserMember userMember = userMemberService.getOne(new LambdaQueryWrapper<UserMember>().eq(UserMember::getUserId, userId));
        return Result.success(userMember);
    }

    @PostMapping("/member")
    public ResultVO openMember() {
        return null;
    }
}