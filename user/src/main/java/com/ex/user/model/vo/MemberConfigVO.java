package com.ex.user.model.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Classname MemberConfigVO
 * @Description
 * @Date 2020/11/18 22:50
 */
@Data
public class MemberConfigVO {
    /**
     * id
     */
    private Integer id;
    /**
     * 会员类型  1：月卡  2：季卡  3：半年   4：一年
     */
    private Integer type;
    /**
     * 会员类型
     */
    private String typeName;
    /**
     * 金额
     */
    private BigDecimal money;

}
