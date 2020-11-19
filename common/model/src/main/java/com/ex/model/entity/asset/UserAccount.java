package com.ex.model.entity.asset;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户账户(user_account)实体类
 *
 * @author 
 * @since 2020-11-19 16:11:11
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user_account")
public class UserAccount{
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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
     * createTime
     */
    private Date createTime;
    /**
     * updateTime
     */
	private Date updateTime;
    /**
     * 状态 1-有效 2-冻结 3-无效
     */
    private Integer status;
    /**
     * version
     */
    private Integer version;

}