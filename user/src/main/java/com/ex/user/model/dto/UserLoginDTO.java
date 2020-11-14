package com.ex.user.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Classname UserLoginDTO
 * @Description TODO
 * @Date 2020/11/14 15:15
 */
@Data
public class UserLoginDTO {
    @NotNull(message = "用户名不能为空")
    private String userName;
    @NotNull(message = "密码不能为空")
    private String password;
}
