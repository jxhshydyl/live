package com.ex.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ex.model.entity.user.UserExpRecord;
import com.ex.model.vo.PageInfoVO;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.MessageDTO;
import com.ex.user.service.UserExpRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname UserExpRecordService
 * @Description TODO
 * @Date 2020/11/15 15:43
 */
@Slf4j
@RestController
@RequestMapping("user/api/{device}/{version}")
public class UserExpRecordController extends BaseController{

    @Autowired
    private UserExpRecordService userExpRecordService;

    @PostMapping("/exp/records")
    public ResultVO addExpRecord() {
        return null;
    }

    @GetMapping("/exp/records")
    public ResultVO getExpRecord(@RequestParam(value = "current", defaultValue = "1") Integer current,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return userExpRecordService.getExpRecord(getUid(),current,size);
    }
}
