package com.ex.user.model.vo;

import lombok.Data;

/**
 * @Classname MemberPrivilegeConfigVO
 * @Description
 * @Date 2020/11/18 22:53
 */
@Data
public class MemberPrivilegeConfigVO {
    /**
     * 会员权限名称
     */
    private String name;
    /**
     * 会员权限小图标
     */
    private String img;
    /**
     * 会员权限展示图
     */
    private String banner;
    /**
     * 描述
     */
    private String description;
    /**
     * 说明
     */
    private String introduce;
}
