package com.ex.zuul.controller;

import com.meizan.ancon.common.enumeration.ResultEnum;
import com.meizan.ancon.common.utils.Result;
import com.meizan.ancon.common.vo.ResultVO;
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
