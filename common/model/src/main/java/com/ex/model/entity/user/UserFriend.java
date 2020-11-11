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
 * (user_friend)实体类
 *
 * @since 2020-11-11 18:24:23
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user_friend")
public class UserFriend extends Model<UserFriend> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
	private Long id;
    /**
     * groupId
     */
    private Long groupId;
    /**
     * 分组名称
     */
    private String groupName;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 好友id
     */
    private Integer friendUserId;
    /**
     * 好友昵称
     */
    private String friendNickName;
    /**
     * 好友头像
     */
    private String friendHeadPortrait;
    /**
     * 状态  0：正常  1：删除
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