package com.ex.user.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Classname UserLoginCodeDTO
 * @Description
 * @Date 2020/11/16 23:50
 */
@Data
public class UserLoginCodeDTO {
    @NotNull(message = "用户名不能为空")
    private String userName;
    @NotNull(message = "验证码不能为空")
    private String code;
}
