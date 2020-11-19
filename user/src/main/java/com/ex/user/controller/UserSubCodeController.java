package com.ex.user.controller;

import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.SubCodeDTO;
import com.ex.user.service.UserSubCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "sub", tags = "订阅码")
public class UserSubCodeController extends BaseController {

    @Autowired
    private UserSubCodeService userSubCodeService;

    @GetMapping("/sub/code")
    @ApiOperation(value = "获取订阅码列表")
    public ResultVO getSubCode(@RequestParam("current") Integer current,
                               @RequestParam("size") Integer size) {
        return userSubCodeService.getSubCode(getUid(), current, size);
    }

    @PostMapping("/sub/code")
    @ApiOperation(value = "生成订阅码")
    public ResultVO createSubCode(@RequestBody SubCodeDTO subCodeDTO) {
        subCodeDTO.setUserId(getUid());
        return userSubCodeService.createSubCode(subCodeDTO);
    }

    @PutMapping("/sub/code/{id}")
    @ApiOperation(value = "修改订阅码")
    public ResultVO updateSubCodeStatus(@PathVariable("id") Long id) {
        Long userId = getUid();
        return userSubCodeService.updateSubCodeStatus(userId, id);
    }

}