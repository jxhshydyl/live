package com.ex.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ex.model.entity.user.UserExpRecord;
import com.ex.model.vo.PageInfoVO;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.mapper.UserExpRecordMapper;
import com.ex.user.service.UserExpRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname UserExpRecordServiceImpl
 * @Description
 * @Date 2020/11/15 15:44
 */
@Slf4j
@Service
public class UserExpRecordServiceImpl extends ServiceImpl<UserExpRecordMapper, UserExpRecord> implements UserExpRecordService {

    @Autowired
    private UserExpRecordMapper userExpRecordMapper;

    @Override
    public ResultVO getExpRecord(Long userId, Integer current, Integer size) {
        Page<UserExpRecord> page = userExpRecordMapper.selectPage(new Page<UserExpRecord>(current, size),
                new LambdaQueryWrapper<UserExpRecord>().eq(UserExpRecord::getUserId, userId));
        PageInfoVO<UserExpRecord> pageInfoVO = new PageInfoVO<UserExpRecord>();
        pageInfoVO.setCount(page.getTotal());
        pageInfoVO.setList(page.getRecords());
        return Result.success(pageInfoVO);
    }
}
