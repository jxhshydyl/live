package com.ex.user.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @Classname UserBankBindDTO
 * @Description
 * @Date 2020/11/14 16:43
 */
@Data
public class UserBankUpdateDTO {
    /**
     * 用户id
     */
    @NotNull
    private Long id;
    /**
     * 用户id
     */
    @Null
    private Long userId;
    /**
     * 1：有效  0：无效
     */
    @NotNull
    private Integer status;

}
