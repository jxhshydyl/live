package com.ex.user.model.dto;

import lombok.Data;

import javax.validation.constraints.Null;

/**
 * @Author: yibu
 * @Date: 17/11/2020 下午 4:10
 * @Version: 1.0
 * @Description:
 */
@Data
public class UserInfoDTO {
    /**
     * userId
     */
    @Null
    private Long userId;
    /**
     * 个人简介
     */
    private String introduce;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像路径
     */
    private String headPortrait;
    /**
     * 性别  1：男  0：女
     */
    private Integer sex;
    /**
     * 所在地
     */
    private String location;
    /**
     * 标签
     */
    private String label;
    /**
     * 背景墙图片
     */
    private String backWallImg;
}
