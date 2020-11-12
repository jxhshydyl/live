package com.ex.file.enums;

public enum ImageSizeEnum {
    SMALL("small", 64, 64),
    MEDIUM("medium", 128, 128),
    LARGE("large", 512, 512),
    ORIGIN("origin", 0, 0),
    ;
    private String name;
    private int width;
    private int height;

    ImageSizeEnum(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
