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
 * 用户粉丝列表(user_fans)实体类
 *
 * @since 2020-11-11 18:24:23
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user_fans")
public class UserFans extends Model<UserFans> implements Serializable {
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
     * 粉丝id
     */
    private Long fansUserId;
    /**
     * 0：无效  1：有效
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