package com.ex.user.model.dto;

import lombok.Data;

/**
 * @Classname UpdateUserSafeDTO
 * @Description
 * @Date 2020/11/13 20:45
 */
@Data
public class UpdateUserSafeDTO {
    private String loginPwd;
    private String code;
}
