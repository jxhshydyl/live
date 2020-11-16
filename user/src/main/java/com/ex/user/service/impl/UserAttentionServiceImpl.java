package com.ex.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ex.model.entity.user.UserAttention;
import com.ex.model.entity.user.UserInfo;
import com.ex.model.enums.ResultEnum;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.user.mapper.UserAttentionMapper;
import com.ex.user.model.dto.UserAttentionDTO;
import com.ex.user.service.UserAttentionService;
import com.ex.user.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 用户关注列表服务接口实现
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:22
 */
@Slf4j
@Service
public class UserAttentionServiceImpl extends ServiceImpl<UserAttentionMapper, UserAttention> implements UserAttentionService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserAttentionMapper userAttentionMapper;

    @Override
    public ResultVO attention(UserAttentionDTO userAttentionDTO) {
        Long userId = userAttentionDTO.getUserId();
        Long attentionUserId = userAttentionDTO.getAttentionUserId();
        UserInfo userInfo = userInfoService.getOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUserId, userAttentionDTO.getUserId()));
        if (userInfo == null) {
            return Result.error(ResultEnum.USER_NOT);
        }
        UserAttention userAttention = userAttentionMapper.selectOne(new LambdaQueryWrapper<UserAttention>()
                .eq(UserAttention::getUserId, userId)
                .eq(UserAttention::getAttentionUserId, attentionUserId));
        if (userAttention == null) {
            userAttention = new UserAttention();
            userAttention.setUserId(userId);
            userAttention.setAttentionUserId(attentionUserId);
            userAttentionMapper.insert(userAttention);
        } else {
            UserAttention temp = new UserAttention();
            temp.setId(userAttention.getId());
            temp.setStatus(userAttentionDTO.getStatus());
            userAttentionMapper.updateById(temp);
        }
        return Result.success();
    }
}