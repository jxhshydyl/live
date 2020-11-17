package com.ex.model.dto;

import lombok.Data;

/**
 * @Classname PageDTO
 * @Description
 * @Date 2020/11/3 20:50
 */
@Data
public abstract class PageDTO {
    private Integer current;
    private Integer limit;
}
