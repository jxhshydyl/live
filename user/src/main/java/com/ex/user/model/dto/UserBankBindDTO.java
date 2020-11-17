package com.ex.user.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

/**
 * @Classname UserBankBindDTO
 * @Description
 * @Date 2020/11/14 16:43
 */
@Data
public class UserBankBindDTO {
    /**
     * 用户id
     */
    @Null
    private Long userId;
    /**
     * 真实姓名
     */
    @NotNull
    private String realName;
    /**
     * 1：银行卡  2：支付宝  3：微信
     */
    private Integer bankType;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 开户行
     */
    @NotNull
    private String openBank;
    /**
     * 支行
     */
    @NotNull
    private String branchBank;
    /**
     * 账号
     */
    @NotNull
    private String account;
    /**
     * 手机号
     */
    private String mobiles;
    /**
     * 图片
     */
    private String img;
    /**
     * 1：有效  0：无效
     */
    @Pattern(regexp = "^1|2$")
    private Integer status;

    /**
     * 短信验证码
     */
    @NotNull
    private String code;
}
