package com.ex.user.model.vo;

import lombok.Data;

/**
 * @Date: 19/11/2020 下午 12:16
 * @Version: 1.0
 * @Description:
 */
@Data
public class ConfigAssetVO {
    /**
     * id
     */
    private Integer id;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 名称
     */
    private String name;
    /**
     * 是否开放提现  0：否  1：是
     */
    private Integer withdrawal;
    /**
     * 是否开放充值  0：否 1：是
     */
    private Integer recharge;
    /**
     * 是否开放兑换  0：否 1：是
     */
    private Integer exchange;
    /**
     * 介绍
     */
    private String declaration;
    /**
     * 小数位
     */
    private Integer digits;
    /**
     * 图标地址
     */
    private String icon;
}
