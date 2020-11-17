package com.ex.user.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Classname UpdateMobileDTO
 * @Description
 * @Date 2020/11/13 23:41
 */
@Data
public class UpdateMobileDTO {
    @NotNull(message = "手机号不能为空")
    private String mobile;
    @NotNull(message = "国家码不能为空")
    private String countryCode;
    @NotNull(message = "旧手机验证码不能为空")
    private String oldDynamicCode;
    @NotNull(message = "新手机验证码不能为空")
    private String newDynamicCode;
}
