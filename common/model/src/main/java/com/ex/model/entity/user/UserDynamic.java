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
 * (user_dynamic)实体类
 *
 * @since 2020-11-11 18:24:23
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user_dynamic")
public class UserDynamic extends Model<UserDynamic> implements Serializable {
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
     * 0：视频  1：动态
     */
    private Integer type;
    /**
     * 标题
     */
    private String title;
    /**
     * 动态内容
     */
    private String content;
    /**
     * 动态图片
     */
    private String imgUrl;
    /**
     * 视频链接
     */
    private String videoUrl;
    /**
     * 点赞
     */
    private Long thumbsCount;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 是否删除   0：未删除   1：删除
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