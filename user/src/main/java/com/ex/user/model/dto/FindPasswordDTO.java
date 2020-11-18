package com.ex.user.model.dto;

import lombok.Data;

/**
 * @Date: 18/11/2020 上午 10:21
 * @Version: 1.0
 * @Description:
 */
@Data
public class FindPasswordDTO {
    private String userName;
    private String countryCode;
    private String code;
    private String loginPwd;
}
