package com.ex.model.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员类型配置(member_config)实体类
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-15 19:57:41
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("member_config")
public class MemberConfig {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 会员类型  1：月卡  2：季卡  3：半年   4：一年
     */
    private Integer type;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 1:有效  0：无效
     */
    private Integer status;
    /**
     * createTime
     */
    private Date createTime;
    /**
     * updateTime
     */
    @TableField(update = "now()")
    private Date updateTime;
    /**
     * 更新用户
     */
    private Integer updateUserId;

}