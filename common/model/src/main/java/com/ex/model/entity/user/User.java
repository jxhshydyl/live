package com.ex.model.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * (user)实体类
 *
 * @since 2020-11-11 18:24:22
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("user")
public class User {

    /**
     * 用户Id
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * 用户名类型 1：手机号码，2：邮箱
     */
    private Integer userType;
    /**
     * 手机号/邮箱
     */
    private String userName;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像路径
     */
    private Integer headPortrait;
    /**
     * 性别  1：男  0：女
     */
    private Integer sex;
    /**
     * 所在地
     */
    private String location;
    /**
     * 国家代码
     */
    private String countryCode;
    /**
     * 登陆密码
     */
    private String loginPwd;
    /**
     * 登陆密码强度
     */
    private Integer loginPwdLevel;
    /**
     * 是否删除(0未删除，1删除)
     */
    private Integer isDeleted;
    /**
     * 是否锁定(0未锁定，1锁定)
     */
    private Integer isLock;
    /**
     * 推荐人Id
     */
    private Long recommendId;
    /**
     * recommendCode
     */
    private String recommendCode;
    /**
     * 是否手机认证(0为未认证，1为已认证)
     */
    private Integer mobileAuth;
    /**
     * 邮箱是否认证(0为未认证，1为已经认证)
     */
    private Integer emailAuth;
    /**
     * 是否实名认证(0为未提交，1，审核中，2审核不通过，3审核通过)
     */
    private Integer realAuth;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 身份证号码
     */
    private String cardId;
    /**
     * 注册时间
     */
    private Date registerTime;
    /**
     * 注册IP
     */
    private String registerIp;
    /**
     * 最后登陆时间
     */
    private Date lastLoginTime;
    /**
     * 最后登陆IP
     */
    private String lastLoginIp;
    /**
     * 是否禁止提现 1:禁止提现  0:可以提现
     */
    private Integer withdraw;

}