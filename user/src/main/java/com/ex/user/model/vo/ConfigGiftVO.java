package com.ex.user.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Date: 19/11/2020 下午 12:16
 * @Version: 1.0
 * @Description:
 */
@Data
public class ConfigGiftVO {
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
     * 余额价值
     */
    private BigDecimal balanceValue;
    /**
     * 积分价值
     */
    private BigDecimal integralValue;
    /**
     * 介绍
     */
    private String declaration;
    /**
     * 图标地址
     */
    private String icon;
}
