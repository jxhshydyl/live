package com.ex.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ex.model.entity.user.User;
import com.ex.model.entity.user.UserSubCode;
import com.ex.model.enums.EnumEither;
import com.ex.model.enums.ResultEnum;
import com.ex.model.vo.PageInfoVO;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.mapper.UserSubCodeMapper;
import com.ex.user.model.dto.SubCodeDTO;
import com.ex.user.service.UserSubCodeService;
import com.ex.user.util.ShareCodeUtil;
import com.ex.user.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 服务接口实现
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-14 11:17:11
 */
@Slf4j
@Service
public class UserSubCodeServiceImpl extends ServiceImpl<UserSubCodeMapper, UserSubCode> implements UserSubCodeService {

    @Autowired
    private UserSubCodeMapper userSubCodeMapper;

    @Override
    public ResultVO getSubCode(Long userId, Integer current, Integer size) {
        Page<UserSubCode> userSubCodePage = userSubCodeMapper.selectPage(new Page<UserSubCode>(current, size),
                new LambdaQueryWrapper<UserSubCode>()
                        .eq(UserSubCode::getUserId, userId));
        PageInfoVO<UserSubCode> pageInfoVO=new PageInfoVO<UserSubCode>();
        pageInfoVO.setList(userSubCodePage.getRecords());
        pageInfoVO.setPage(current);
        pageInfoVO.setLimit(size);
        pageInfoVO.setCount(userSubCodePage.getTotal());
        return null;
    }

    @Override
    public ResultVO createSubCode(SubCodeDTO subCodeDTO) {
        Integer time = subCodeDTO.getTime();
        Date beginTime = new Date();
        Date endTime = TimeUtil.getAfterHourTime(new Date(), time);
        String code = ShareCodeUtil.idToCode(subCodeDTO.getUserId(), System.currentTimeMillis());
        UserSubCode userSubCode = userSubCodeMapper.selectOne(new LambdaQueryWrapper<UserSubCode>()
                .ge(UserSubCode::getBeginTime, beginTime)
                .le(UserSubCode::getEndTime, endTime)
                .eq(UserSubCode::getBeginTime, code)
                .last("limit 1"));
        int limit = 0;
        while (userSubCode != null && limit < 3) {
            code = ShareCodeUtil.idToCode(subCodeDTO.getUserId(), System.currentTimeMillis());
            userSubCode = userSubCodeMapper.selectOne(new LambdaQueryWrapper<UserSubCode>()
                    .ge(UserSubCode::getBeginTime, beginTime)
                    .le(UserSubCode::getEndTime, endTime)
                    .eq(UserSubCode::getBeginTime, code)
                    .last("limit 1"));
            if (userSubCode == null) {
                break;
            }
            limit++;
        }
        if (userSubCode == null) {
            userSubCode = new UserSubCode();
            userSubCode.setUserId(subCodeDTO.getUserId());
            userSubCode.setCode(code);
            userSubCode.setBeginTime(beginTime);
            userSubCode.setEndTime(endTime);
            userSubCodeMapper.insert(userSubCode);
            return Result.success(userSubCode);
        }
        return Result.error(ResultEnum.USER_SUB_CODE_FAIL);
    }

    @Override
    public ResultVO updateSubCodeStatus(Long userId, Long id) {
        UserSubCode userSubCode = userSubCodeMapper.selectById(id);
        if (!userSubCode.getUserId().equals(userId)) {
            Result.error(ResultEnum.USER_NOT_SUB_CODE);
        }
        Integer status = EnumEither.INVALID.getCode();
        if (userSubCode.getStatus() == EnumEither.INVALID.getCode()) {
            status = EnumEither.EFFECTIVE.getCode();
        }
        UserSubCode temp = new UserSubCode();
        temp.setId(id);
        temp.setStatus(status);
        userSubCodeMapper.updateById(temp);
        return Result.success();
    }
}