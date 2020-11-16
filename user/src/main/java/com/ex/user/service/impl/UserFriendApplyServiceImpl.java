package com.ex.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ex.model.entity.user.UserFriendApply;
import com.ex.model.vo.ResultVO;
import com.ex.user.mapper.UserFriendApplyMapper;
import com.ex.user.model.dto.UserFriendApplyDTO;
import com.ex.user.service.UserFriendApplyService;
import lombok.extern.slf4j.Slf4j;
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
public class UserFriendApplyServiceImpl extends ServiceImpl<UserFriendApplyMapper, UserFriendApply> implements UserFriendApplyService {

    @Autowired
    private UserFriendApplyMapper userFriendApplyMapper;

    @Override
    public ResultVO getFriendApply(Long userId, Integer current, Integer size) {
        return null;
    }

    @Override
    public ResultVO apply(UserFriendApplyDTO userFriendApplyDTO) {
        //判断是否存在未处理的

        return null;
    }
}