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
 * (user_reply)实体类
 *
 * @author jxhshydyl
 * @since 2020-11-11 18:24:23
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user_reply")
public class UserReply extends Model<UserReply> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
	private Long id;
    /**
     * 动态id
     */
    private Long dynamicId;
    /**
     * 评论id
     */
    private Long commentId;
    /**
     * 回复父id
     */
    private Long parentId;
    /**
     * 回复用户id
     */
    private Long userId;
    /**
     * 用户头像
     */
    private String headPortrait;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 回复内容
     */
    private String comment;
    /**
     * 状态 0：正常  1：删除
     */
    private Integer status;
    /**
     * 回复次数
     */
    private Integer replyCount;
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