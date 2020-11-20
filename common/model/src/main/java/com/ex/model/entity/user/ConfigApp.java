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
 * app版本更新配置(config_app)实体类
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-20 17:29:14
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("config_app")
public class ConfigApp {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 客户端(android,ios)
     */
    private String client;
    /**
     * 版本号
     */
    private String version;
    /**
     * 内部版本号
     */
    private Integer build;
    /**
     * 下载URL
     */
    private String url;
    /**
     * 更新内容
     */
    private String description;
    /**
     * en更新内容
     */
    private String enDescription;
    /**
     * 是否强制升级  1：是  0：否
     */
    private Integer remind;
    /**
     * downloadUrl
     */
    private String downloadUrl;
    /**
     * createTime
     */
    private Date createTime;
    /**
     * 发布时间
     */
    private Date updateTime;
}