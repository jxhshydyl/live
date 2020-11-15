package com.ex.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ex.model.entity.user.UserInfo;
import com.ex.model.entity.user.UserSignRecord;
import com.ex.model.enums.ResultEnum;
import com.ex.model.exceptions.BusinessException;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.mapper.UserSignRecordMapper;
import com.ex.user.service.UserInfoService;
import com.ex.user.service.UserSignRecordService;
import com.ex.user.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Classname UserSignRecordServiceImpl
 * @Description TODO
 * @Date 2020/11/15 17:15
 */
@Slf4j
@Service
public class UserSignRecordServiceImpl extends ServiceImpl<UserSignRecordMapper, UserSignRecord> implements UserSignRecordService {

    @Autowired
    private UserSignRecordMapper userSignRecordMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO signIn(Long userId) {
        UserInfo userInfo = userInfoService.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUserId, userId));
        if (userInfo == null) {
            throw new BusinessException(ResultEnum.USER_NOT);
        }
        Integer contDays = userInfo.getContDays() == null ? 0 : userInfo.getContDays();
        Date lastSignTime = userInfo.getLastSignTime();
        if (TimeUtil.isYesterday(lastSignTime)) {
            contDays = contDays + 1;
        } else {
            contDays = 1;
        }
        UserInfo temp = new UserInfo();
        temp.setId(userId);
        temp.setLastSignTime(new Date());
        temp.setContDays(contDays);
        userInfoService.updateById(temp);
        UserSignRecord userSignRecord = new UserSignRecord();
        userSignRecord.setUserId(userId);
        userSignRecord.setExp(0);
        userSignRecord.setIntegralNum(BigDecimal.ZERO);
        userSignRecordMapper.insert(userSignRecord);
        return Result.success();
    }
}
