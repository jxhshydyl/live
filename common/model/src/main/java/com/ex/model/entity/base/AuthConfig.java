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
 * 等级权限配置表(auth_config)实体类
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-15 19:57:41
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("auth_config")
public class AuthConfig {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 权限key
     */
    private String authKey;
    /**
     * 权限值
     */
    private String authValue;
    /**
     * 权限图片
     */
    private String img;
    /**
     * 最低等级
     */
    private Integer level;
    /**
     * 1：有效 0：无效
     */
    private Integer status;
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