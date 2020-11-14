package com.ex.model.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * (user_sub_code)实体类
 *
 * @since 2020-11-14 11:17:11
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user_sub_code")
public class UserSubCode {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * userId
     */
    private Long userId;
    /**
     * 订阅码
     */
    private String code;
    /**
     * 有效开始时间
     */
    private Date beginTime;
    /**
     * 有效结束时间
     */
    private Date endTime;
    /**
     * 是否有效  1：有效   0：停用
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