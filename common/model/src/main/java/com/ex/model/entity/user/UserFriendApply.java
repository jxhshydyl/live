package com.ex.model.entity.user;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (user_friend_apply)实体类
 *
 * @author jxhshydyl
 * @since 2020-11-11 18:24:23
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user_friend_apply")
public class UserFriendApply extends Model<UserFriendApply> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
	private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 目标用户id
     */
    private Long targetUserId;
    /**
     * 目标用户昵称
     */
    private String targetNickName;
    /**
     * 目标用户头像
     */
    private String targetHeadPortrait;
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
    @TableField(update = "now()")
	private Date updateTime;

}