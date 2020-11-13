package com.ex.user.model.dto;

import lombok.Data;

/**
 * @Classname UpdateUserSafeDTO
 * @Description TODO
 * @Date 2020/11/13 20:45
 */
@Data
public class UpdateUserSafeDTO {
    private String newLoginPwd;
    private String dynamicCode;
}
