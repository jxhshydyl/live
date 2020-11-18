package com.ex.model.enums;

/**
 * @Date: 20/10/2020 下午 6:09
 * @Version: 1.0
 * @Description:
 */
public enum EnumFileType {
    /***/
    ICON(1, "图标", "icon/"),
    CAROUSEL(2, "轮播图", "carousel/");

    private int id;

    private String name;

    private String field;

    /**
     * field为对应UserFund表里的一个冻结资金字段
     *
     * @param id
     * @param name
     * @param field
     */
    EnumFileType(int id, String name, String field) {
        this.id = id;
        this.name = name;
        this.field = field;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getField() {
        return field;
    }
}

