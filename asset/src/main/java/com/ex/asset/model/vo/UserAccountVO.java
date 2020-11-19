package com.ex.asset.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Date: 19/11/2020 下午 5:52
 * @Version: 1.0
 * @Description:
 */
@Data
public class UserAccountVO {
    /**
     * id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 账户类型
     */
    private Integer type;
    /**
     * 资产id
     */
    private Integer assetId;
    /**
     * 资产名称
     */
    private String assetName;
    /**
     * 可用余额
     */
    private BigDecimal balance;
    /**
     * 冻结资金
     */
    private BigDecimal freeze;
    /**
     * 状态 1-有效 2-冻结 3-无效
     */
    private Integer status;
}
