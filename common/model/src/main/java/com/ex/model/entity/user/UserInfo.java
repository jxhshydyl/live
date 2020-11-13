package com.ex.model.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * (user_info)实体类
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:23
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user_info")
public class UserInfo extends Model<UserInfo> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * userId
     */
    private Integer userId;
    /**
     * 个人简介
     */
    private String introduce;
    /**
     * 标签
     */
    private String label;
    /**
     * 背景墙图片
     */
    private String backWallImg;
    /**
     * 点赞
     */
    private Long thumbsCount;
    /**
     * 关注
     */
    private Integer concernCount;
    /**
     * 粉丝数
     */
    private Integer fansCount;
    /**
     * VIP 等级
     */
    private Integer vipLevel;
    /**
     * 经验等级
     */
    private Integer experienceLevel;
    /**
     * 经验值
     */
    private Integer experienceValue;
    /**
     * createTime
     */
    private Date createTime;
    /**
     * updateTime
     */
    @TableField(update = "now()")
    private Date updateTime;

}