package com.ex.user.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @Classname UserAuthDTO
 * @Description
 * @Date 2020/11/11 22:57
 */
@Data
public class UserAuthDTO {
    /**
     * 用户ID
     */
    @Null
    private Long userId;
    /**
     * 国家区号代码
     */
    @NotNull
    private String countryCode;
    /**
     * 证件类型（1，身份证，2护照）
     */
    private Integer cardType;
    /**
     * 姓名
     */
    @NotNull
    private String realName;
    /**
     * 证件号
     */
    @NotNull
    private String cardNumber;
    /**
     * 手持证件照URL (正反一起通过,分割)
     */
    @NotNull
    private String cardImg;
}
