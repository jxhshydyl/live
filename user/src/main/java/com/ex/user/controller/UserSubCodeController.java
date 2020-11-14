package com.ex.user.controller;

import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.SubCodeDTO;
import com.ex.user.service.UserSubCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 服务控制器
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-14 11:17:11
 */
@Slf4j
@RestController
@RequestMapping("user/api/{device}/{version}")
public class UserSubCodeController extends BaseController {

    @Autowired
    private UserSubCodeService userSubCodeService;

    @GetMapping("/sub/code")
    public ResultVO getSubCode(@RequestParam("current") Integer current,
                               @RequestParam("size") Integer size) {
        return userSubCodeService.getSubCode(getUid(), current, size);
    }

    @PostMapping("/sub/code")
    public ResultVO createSubCode(@RequestBody SubCodeDTO subCodeDTO) {
        subCodeDTO.setUserId(getUid());
        return userSubCodeService.createSubCode(subCodeDTO);
    }

    @PutMapping("/sub/code/{id}")
    public ResultVO updateSubCodeStatus(@PathVariable("id") Long id) {
        Long userId = getUid();
        return userSubCodeService.updateSubCodeStatus(userId, id);
    }

}