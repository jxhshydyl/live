package com.ex.user.controller;

import com.ex.model.vo.ResultVO;
import com.ex.user.service.UserSignRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname UserSignRecordController
 * @Description
 * @Date 2020/11/15 17:16
 */
@Slf4j
@RestController
@RequestMapping("user/api/{device}/{version}")
@Api(value = "sign", tags = "签到")
public class UserSignRecordController extends BaseController {

    @Autowired
    private UserSignRecordService userSignRecordService;

    @PostMapping("/sign")
    @ApiOperation(value = "签到")
    public ResultVO signIn() {
        return userSignRecordService.signIn(getUid());
    }
}
