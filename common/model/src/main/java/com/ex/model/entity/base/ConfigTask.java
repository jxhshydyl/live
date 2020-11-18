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
 * 任务配置(config_task)实体类
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-18 23:25:42
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("config_task")
public class ConfigTask {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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
     * 状态  1：有效  0：无效
     */
    private Integer status;
    /**
     * 顺序  越大越前
     */
    private Integer sort;
    /**
     * createTime
     */
    private Date createTime;
    /**
     * updateTime
     */
    private Date updateTime;
    /**
     * 更新用户
     */
    private Integer updateUserId;
}