package com.ex.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ex.model.entity.user.UserFriendApply;
import com.ex.model.vo.ResultVO;
import com.ex.user.model.dto.UserFriendApplyDTO;

/**
 * 服务接口
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:23
 */
public interface UserFriendApplyService extends IService<UserFriendApply> {

    ResultVO getFriendApply(Long userId, Integer current, Integer size);

    ResultVO apply(UserFriendApplyDTO userFriendApplyDTO);

}
