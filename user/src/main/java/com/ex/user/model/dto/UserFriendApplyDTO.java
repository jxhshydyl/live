package com.ex.user.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @Date: 16/11/2020 下午 6:07
 * @Version: 1.0
 * @Description:
 */
@Data
public class UserFriendApplyDTO {
    /**
     * 用户id
     */
    @Null
    private Long userId;
    /**
     * 目标用户id
     */
    @NotNull
    private Long targetUserId;
    /**
     * 申请内容
     */
    private String applyContent;
}
