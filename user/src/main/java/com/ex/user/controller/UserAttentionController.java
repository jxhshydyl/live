package com.ex.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ex.model.entity.user.UserAttention;
import com.ex.model.vo.PageInfoVO;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.UserAttentionDTO;
import com.ex.user.service.UserAttentionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:22
 */
@Slf4j
@RestController
@RequestMapping("user/api/{device}/{version}")
public class UserAttentionController extends BaseController {

    @Autowired
    private UserAttentionService userAttentionService;

    @PostMapping("/attention")
    public ResultVO attention(@RequestBody @Validated UserAttentionDTO userAttentionDTO) {
        Long userId = getUid();
        userAttentionDTO.setUserId(userId);
        return userAttentionService.attention(userAttentionDTO);
    }

    @GetMapping("/attention")
    public ResultVO getUserAttention(@RequestParam("current") Integer current,
                                     @RequestParam("size") Integer size) {
        Long userId = getUid();
        Page<UserAttention> page = userAttentionService.page(new Page<UserAttention>(current, size),
                new LambdaQueryWrapper<UserAttention>().eq(UserAttention::getUserId, userId));
        PageInfoVO<UserAttention> pageInfoVO = new PageInfoVO<UserAttention>();
        pageInfoVO.setCount(page.getTotal());
        pageInfoVO.setList(page.getRecords());
        return Result.success(pageInfoVO);
    }

    @GetMapping("/fans")
    public ResultVO getFans(@RequestParam("current") Integer current,
                            @RequestParam("size") Integer size) {
        Long userId = getUid();
        Page<UserAttention> page = userAttentionService.page(new Page<UserAttention>(current, size),
                new LambdaQueryWrapper<UserAttention>().eq(UserAttention::getAttentionUserId, userId));
        PageInfoVO<UserAttention> pageInfoVO = new PageInfoVO<UserAttention>();
        pageInfoVO.setCount(page.getTotal());
        pageInfoVO.setList(page.getRecords());
        return Result.success(pageInfoVO);
    }
}