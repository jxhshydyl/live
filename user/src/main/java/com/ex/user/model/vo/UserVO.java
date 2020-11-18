package com.ex.user.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Classname UserVO
 * @Description
 * @Date 2020/11/11 20:31
 */
@Data
public class UserVO {
    /**
     * 用户Id
     */
    private Long id;
    /**
     * 用户类型 0：普通用户   1：讲师
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
     * 是否禁止提现 1:禁止提现  0:可以提现
     */
    private Integer withdraw;

    private UserInfoVO userInfoVO;
}
