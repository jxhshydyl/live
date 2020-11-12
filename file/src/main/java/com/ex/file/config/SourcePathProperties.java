package com.ex.file.config;

import org.springframework.stereotype.Component;

import java.io.File;

/**
 * todo  改成可以配置的
 */
@Component
public class SourcePathProperties {

    public static String PRIVATE_UPLOAD_PATH = "/home/www/file/private";
    public static String PUBLIC_UPLOAD_PATH = "/home/www/file/public";
    public static Integer FILE_MAX_SIZE = 5242880;
    public static String DOMAIN = "";
    // 默认404图片
    public static String DEFAULT_404_IMAGE = SourcePathProperties.class.getResource("/").getPath() + "404.jpeg";

    static {
        // 检测目录是否存在
        File file = new File(PRIVATE_UPLOAD_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file1 = new File(PUBLIC_UPLOAD_PATH);
        if (!file1.exists()) {
            file1.mkdirs();
        }
    }
}
