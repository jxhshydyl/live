package com.ex.model.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * (user_friend_apply)实体类
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-11 18:24:23
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user_friend_apply")
public class UserFriendApply {
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
     * 目标用户id
     */
    private Long targetUserId;
    /**
     * 申请内容
     */
    private String applyContent;
    /**
     * 0：申请中  1：通过  2：拒绝
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
}