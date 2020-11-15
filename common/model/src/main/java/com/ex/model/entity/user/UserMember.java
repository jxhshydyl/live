package com.ex.model.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * (user_member)实体类
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-15 19:48:00
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user_member")
public class UserMember {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * userId
     */
    private Long userId;
    /**
     * 会员类型 0：普通会员
     */
    private String type;
    /**
     * 有效时间
     */
    private Date validityTime;
    /**
     * 是否续费  1：续费  0：非续费
     */
    private Integer renew;
    /**
     * createTime
     */
    private Date createTime;
    /**
     * updateTime
     */
    private Date updateTime;

}