package com.ex.user.controller;

import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.UserFriendApplyDTO;
import com.ex.user.service.UserFriendApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 服务控制器
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:23
 */
@Slf4j
@RestController
@RequestMapping("user/api/{device}/{version}")
public class UserFriendApplyController extends BaseController {

    @Autowired
    private UserFriendApplyService userFriendApplyService;

    @GetMapping("/friend/apply")
    public ResultVO getFriendApply(@RequestParam("current") Integer current,
                                   @RequestParam("current") Integer size) {
        return userFriendApplyService.getFriendApply(getUid(),current,size);
    }

    @PostMapping("/friend/apply")
    public ResultVO apply(@RequestBody @Validated UserFriendApplyDTO userFriendApplyDTO) {
        return userFriendApplyService.apply(userFriendApplyDTO);
    }

}