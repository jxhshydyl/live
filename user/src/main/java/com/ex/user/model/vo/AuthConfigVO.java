package com.ex.user.model.vo;

import lombok.Data;

/**
 * @Classname AuthConfigVO
 * @Description
 * @Date 2020/11/18 22:31
 */
@Data
public class AuthConfigVO {
    /**
     * 权限值
     */
    private String authValue;
    /**
     * 权限图片
     */
    private String img;
}
