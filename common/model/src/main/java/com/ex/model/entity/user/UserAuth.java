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
 * (user_auth)实体类
 *
 * @author jxhshydyl
 * @since 2020-11-11 18:24:22
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user_auth")
public class UserAuth extends Model<UserAuth> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
	private Integer id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 国家区号代码
     */
    private String countrycode;
    /**
     * 证件类型（1，身份证，2护照）
     */
    private Integer cardType;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 证件号
     */
    private String cardNumber;
    /**
     * 手持证件照URL
     */
    private String cardImg;
    /**
     * 申请时间
     */
    private Date createTime;
    /**
     * （0审核中，1审核不通过，2审核通过）
     */
    private Integer status;
    /**
     * 失败原因
     */
    private String failReason;
    /**
     * 管理员ID
     */
    private Integer adminId;
    /**
     * 审核时间
     */
    private Date authTime;

}