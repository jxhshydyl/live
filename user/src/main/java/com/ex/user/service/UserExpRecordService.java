package com.ex.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ex.model.entity.user.UserExpRecord;
import com.ex.model.vo.ResultVO;

/**
 * @Classname UserExpRecordService
 * @Description
 * @Date 2020/11/15 15:43
 */
public interface UserExpRecordService extends IService<UserExpRecord> {

    ResultVO getExpRecord(Long userId, Integer current, Integer size);

}
