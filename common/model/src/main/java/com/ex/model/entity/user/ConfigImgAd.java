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
 * 轮播图配置(config_img_ad)实体类
 *
 * @author
 * @description 由 Mybatisplus Code Generator 创建
 * @since 2020-11-20 17:29:15
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("config_img_ad")
public class ConfigImgAd {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 标题
     */
    private String name;
    /**
     * 图片地址
     */
    private String img;
    /**
     * 链接地址
     */
    private String address;
    /**
     * 备注
     */
    private String remark;
    /**
     * 排序
     */
    private Integer orderBy;
    /**
     * isDeleted
     */
    private Integer isDeleted;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 设备类型
     */
    private Integer deviceType;
    /**
     * 多语言
     */
    private String language;
    /**
     * 用途类别
     */
    private Integer categoryType;
    /**
     * 是否推荐  1：是  0：否
     */
    private Integer isRecommend;
    /**
     * createTime
     */
    private Date createTime;
    /**
     * updateTime
     */
    private Date updateTime;

}