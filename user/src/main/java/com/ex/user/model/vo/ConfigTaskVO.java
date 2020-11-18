package com.ex.user.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Classname ConfigTaskVO
 * @Description
 * @Date 2020/11/18 23:33
 */
@Data
public class ConfigTaskVO {
    private Integer id;
    /**
     * 任务类型  0：一次性任务   1：持续性任务
     */
    private Integer type;
    /**
     * 等级限制
     */
    private Integer level;
    /**
     * 会员权限名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 奖励经验
     */
    private Integer rewardExp;
    /**
     * 奖励积分
     */
    private BigDecimal rewardIntegral;
    /**
     * 是否完成  1：是  0：否
     */
    private Integer complete;
    /**
     * 是否领取  1：是  0：否
     */
    private Integer receive;
}
