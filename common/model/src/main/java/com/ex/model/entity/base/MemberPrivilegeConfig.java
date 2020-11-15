package com.ex.model.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 会员特权配置(member_privilege_config)实体类
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-15 19:57:41
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("member_privilege_config")
public class MemberPrivilegeConfig {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 会员权限名称
     */
    private String name;
    /**
     * 会员权限小图标
     */
    private String img;
    /**
     * 会员权限展示图
     */
    private String banner;
    /**
     * 描述
     */
    private String describe;
    /**
     * 说明
     */
    private String introduce;
    /**
     * 状态  1：有效  0：无效
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