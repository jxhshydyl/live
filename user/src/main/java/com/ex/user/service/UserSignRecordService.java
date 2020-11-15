package com.ex.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ex.model.entity.user.UserSignRecord;
import com.ex.model.vo.ResultVO;

/**
 * @Classname UserSignRecordService
 * @Description TODO
 * @Date 2020/11/15 17:14
 * @Author by liuweipeng
 */
public interface UserSignRecordService extends IService<UserSignRecord> {

    ResultVO signIn(Long userId);
}
