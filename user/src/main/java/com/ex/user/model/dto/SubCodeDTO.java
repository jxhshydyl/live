package com.ex.user.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @Classname SubCodeDTO
 * @Description TODO
 * @Date 2020/11/14 11:23
 */
@Data
public class SubCodeDTO {
    @NotNull(message = "有效时长不能为空")
    private Integer time;
    @Null
    private Long userId;
}
