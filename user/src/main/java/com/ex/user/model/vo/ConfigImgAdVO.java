package com.ex.user.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Date: 20/11/2020 下午 5:37
 * @Version: 1.0
 * @Description:
 */
@Data
public class ConfigImgAdVO {
    /**
     * id
     */
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
}
