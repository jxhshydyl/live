package com.ex.model.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 礼物配置(config_gift)实体类
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-19 12:02:06
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("config_gift")
public class ConfigGift {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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
     * 是否显示 0-否 1- 是
     */
    private Integer display;
    /**
     * 是否删除   0：否   1：是
     */
    private Integer isDelete;
    /**
     * 是否有效 1：有效  0：无效
     */
    private Integer status;
    /**
     * 图标地址
     */
    private String icon;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * createdAt
     */
    private Date createdAt;
    /**
     * updatedAt
     */
    private Date updatedAt;
}