package com.ex.model.entity.user;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (user_bank)实体类
 *
 * @since 2020-11-11 18:24:22
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user_bank")
public class UserBank extends Model<UserBank> implements Serializable {
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
     * 用户名称
     */
    private String userName;
    /**
     * 真实名称
     */
    private String realName;
    /**
     * 1：银行卡  2：支付宝  3：微信
     */
    private Integer bankType;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 开户行
     */
    private String openBank;
    /**
     * 账号
     */
    private String account;
    /**
     * 手机号
     */
    private String mobiles;
    /**
     * 图片
     */
    private String img;
    /**
     * 提交时间
     */
    private Date submitTime;
    /**
     * 1：有效  0：无效
     */
    private Integer status;
    /**
     * 是否删除  1：删除  0：正常
     */
    private Integer isDeleted;

}