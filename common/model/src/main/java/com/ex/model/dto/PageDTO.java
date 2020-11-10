package com.ex.model.dto;

import lombok.Data;

/**
 * @Classname PageDTO
 * @Description TODO
 * @Date 2020/11/3 20:50
 * @Author by liuweipeng
 */
@Data
public abstract class PageDTO {
    private Integer current;
    private Integer limit;
}
