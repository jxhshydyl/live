package com.ex.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * (enterprise)实体类
 *
 * @author kancy
 * @since 2020-11-02 14:53:07
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
public class Enterprise {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Integer id;
    /**
     * name
     */
    private String name;
    /**
     * mobile
     */
    private String mobile;
    /**
     * email
     */
    private String email;
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