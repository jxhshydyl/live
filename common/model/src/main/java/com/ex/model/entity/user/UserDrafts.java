package com.ex.model.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * (user_drafts)实体类
 *
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-14 10:55:07
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user_drafts")
public class UserDrafts {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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
     * 是否删除  0：未删除   1：删除
     */
    private Integer isDelete;
    /**
     * createTime
     */
    private Date createTime;
    /**
     * updateTime
     */
    private Date updateTime;

}