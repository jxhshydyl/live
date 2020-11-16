package com.ex.user.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @Date: 16/11/2020 上午 11:53
 * @Version: 1.0
 * @Description:
 */
@Data
public class UserAttentionDTO {
    @Null
    private Long userId;
    @NotNull
    private Long attentionUserId;
    @NotNull
    private Integer status;
}
