package com.ex.user.model.dto;

import lombok.Data;

/**
 * @Classname UserDTO
 * @Description
 * @Date 2020/11/11 20:17
 */
@Data
public class UserDTO {
    /**
     * 手机号/邮箱
     */
    private String userName;
    /**
     * 密码
     */
    private String loginPwd;
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

    private String recommendCode;

    private String code;
}
