package com.ex.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ex.model.entity.user.UserInfo;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.mapper.UserInfoMapper;
import com.ex.user.model.dto.UserInfoDTO;
import com.ex.user.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务接口实现
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:23
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public ResultVO updateUserInfo(UserInfoDTO userInfoDTO) {
        UserInfo userInfo = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                        .eq(UserInfo::getUserId, userInfoDTO.getUserId()));
        UserInfo temp = new UserInfo();
        BeanUtils.copyProperties(userInfoDTO, temp);
        temp.setId(userInfo.getId());
        userInfoMapper.updateById(temp);
        return Result.success();
    }
}