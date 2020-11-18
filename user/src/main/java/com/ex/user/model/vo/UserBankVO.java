package com.ex.user.model.vo;

import lombok.Data;

/**
 * @Classname UserBankVO
 * @Description
 * @Date 2020/11/18 21:39
 */
@Data
public class UserBankVO {
    /**
     * id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 真实名称
     */
    private String realName;
    /**
     * 1：银行卡  2：支付宝  3：微信
     */
    private Integer type;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 开户行
     */
    private String openBank;
    /**
     * 支行
     */
    private String branchBank;
    /**
     * 账号
     */
    private String account;
    /**
     * 1：有效  0：无效
     */
    private Integer status;
}
