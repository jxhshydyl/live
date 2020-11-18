package com.ex.util.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ImageFileUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageFileUtil.class);

    private static final String publicUrl = "";

    private static String[] LegalSuffix = new String[]{"jpg", "jpeg", "png"};


    /**
     * 处理图片上传, 流式接收
     */
    public static String receiveUploadImage(MultipartFile request, String saveFilepath) {
        try {
            File file = new File(saveFilepath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            request.transferTo(file);
        } catch (Exception e) {
            logger.error("上传失败异常:", e);
            throw new RuntimeException("上传失败: 参考值10112");
        }
        return saveFilepath;
    }


    // 取出当前日期字符串
    private static String getTodayStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date dt = new Date();
        return sdf.format(dt);
    }


    /**
     * 生成公开文件路径
     */
    public static String generatePublicFilepath(String suffix) {
        return String.format("%s/%s/%s.%s", publicUrl, getTodayStr(), UUID.randomUUID(), suffix);
    }

    public static String generatePublicFilepath() {
        return String.format("%s/%s/%s.%s", publicUrl, getTodayStr(), UUID.randomUUID(), "jpg");
    }

    public static String generateOssFilepath() {
        return String.format("%s", UUID.randomUUID());
    }


    /**
     * 检查文件合法性
     */
    public static boolean checkFilenameLegal(String filename) {
        Filepath filepath = new Filepath(filename);
        if (!filepath.isLegal) {
            return false;
        }
        // 检查 后缀名合法性
        for (String s : LegalSuffix) {
            if (s.equals(filepath.suffix)) {
                return true;
            }
        }
        return false;
    }


    /**
     * @param realpath 如 /User/kidd/tt/private/20180501/fa6cbcbb-bee7-41cd-9235-6bcdd7fd5b57.large.jpg
     * @return 如 20180501/fa6cbcbb-bee7-41cd-9235-6bcdd7fd5b57.large.jpg
     */
    public static String realFilepath2PublicUri(String realpath) {
        if (!realpath.startsWith(publicUrl)) {
            logger.error("文件: " + realpath);
            logger.error("不匹配路径: " + publicUrl);
            throw new RuntimeException("内部错误: 参考值10034");
        }
        return realpath.replace(publicUrl + "/", "");
    }

}
