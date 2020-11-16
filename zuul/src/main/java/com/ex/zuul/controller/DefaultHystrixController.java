package com.ex.zuul.controller;

import com.ex.model.enums.ResultEnum;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 默认降级处理
 */
@RestController
public class DefaultHystrixController {

    @RequestMapping("/defaultfallback")
    public ResultVO defaultfallback() {
        return Result.error(ResultEnum.serverFallback.getCode());
    }
}
