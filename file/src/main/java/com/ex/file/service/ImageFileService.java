package com.ex.file.service;

import com.ex.file.config.SourcePathProperties;
import com.ex.file.enums.ImageSizeEnum;
import com.ex.file.exception.CustomException;
import com.ex.file.service.entity.Filepath;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class ImageFileService {
    private static final Logger logger = LoggerFactory.getLogger(ImageFileService.class);

    private static String[] LegalSuffix = new String[]{"jpg", "jpeg", "png"};


    public static ImageSizeEnum getImageSizeEnum(String size) {
        ImageSizeEnum sizeEnum;
        switch (size.toLowerCase()) {
            case "small":
                sizeEnum = ImageSizeEnum.SMALL;
                break;
            case "medium":
                sizeEnum = ImageSizeEnum.MEDIUM;
                break;
            case "large":
                sizeEnum = ImageSizeEnum.LARGE;
                break;
            default:
                sizeEnum = ImageSizeEnum.ORIGIN;
                break;
        }
        return sizeEnum;
    }

    /**
     * 处理图片上传, 流式接收
     */
    public static void receiveUploadImage(HttpServletRequest request, String saveFilepath) throws CustomException {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        RandomAccessFile random = null;
        RandomAccessFile random2 = null;
        String app = request.getHeader("appType");

        try {
            inputStream = request.getInputStream();
            File file = new File(saveFilepath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fileOutputStream = new FileOutputStream(saveFilepath);
            byte[] bbuf = new byte[8192];
            int size = 0;
            while ((size = inputStream.read(bbuf, 0, 8192)) > 0) {
                fileOutputStream.write(bbuf, 0, size);
            }
            /*
             * 但是需要注意，采用这种原始的方式写入文件时，你会发现被写入的文件内容前4行并非是读取文件的真正内容，
             * 从第四行开始才是正文数据。第二行是文件路径以及名称。所以通常的做法是，先将文件写入临时文件中，然后
             * 再采用RandomAccessFile读取临时文件的第四行以后部分。写入到目标文件中。
             *
             * 2019.4.18 读取 行数包含 Content-Type, 这从一行找下几行的空行, 空行以下就是图片内容.
             */

            // 读取临时文件
            random = new RandomAccessFile(saveFilepath, "r");
            random.seek(0);
            boolean reachSplitLine = false;
            while (true) {
                String line = random.readLine();
                logger.info("-------->读取图片行:" + line);
                if (reachSplitLine && StringUtils.isBlank(line)) {
                    break;
                }
                if (line.contains("Content-Type")) {
                    reachSplitLine = true;
                }
            }
            random2 = new RandomAccessFile(saveFilepath, "rw");
            while (random.getFilePointer() < random.length()) {
                random2.writeByte(random.readByte());
            }

            /////////////////////////////////////////////////////////////////////
//            int second = 1;
//            String secondLine = null;
//            while (second <= 2) {
//                secondLine = random.readLine();
//                second++;
//            }
//            int position = secondLine.lastIndexOf('\\');
//            secondLine.substring(position + 1, secondLine.length() - 1);
//            random.seek(0);
//            long forthEndPosition = 0;
//            int forth = 1;
//            while ((n = random.readByte()) != -1 && (forth <= 4)) {
//                if (n == '\n') {
//                    forthEndPosition = random.getFilePointer();
//                    forth++;
//                }
//            }
//            random2 = new RandomAccessFile(saveFilepath, "rw");
//            random.seek(random.length());
//            long endPosition = random.getFilePointer();
//            long mark = endPosition;
//            int j = 1;
//            while ((mark >= 0) && (j <= 6)) {
//                mark--;
//                random.seek(mark);
//                n = random.readByte();
//                if (n == '\n') {
//                    endPosition = random.getFilePointer();
//                    j++;
//                }
//            }
//            random.seek(forthEndPosition);
//            long startPoint = random.getFilePointer();
//
//            random.seek(startPosition);
//            random2 = new RandomAccessFile(saveFilepath, "rw");
//            while (startPosition < endPosition - 1) {
//                n = random.readByte();
//                random2.write(n);
//                startPosition = random.getFilePointer();
//            }

        } catch (IOException e) {
            logger.error("处理出错: " + e.getMessage());
            throw new CustomException("文件上传模式不支持");
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new CustomException("上传失败: 参考值10112");
        } finally {
            try {
                if (fileOutputStream != null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (random != null){
                    random.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (random2 != null) random2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // 取出当前日期字符串
    private static String getTodayStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date dt = new Date();
        return sdf.format(dt);
    }

    /**
     * 生成私密文件路径
     */
    public static String generatePrivateFilepath(String userId) {
        return String.format("%s/%s/%s_%s.%s",
                SourcePathProperties.PRIVATE_UPLOAD_PATH, getTodayStr(), userId, UUID.randomUUID(), "jpg");
    }

    public static String generatePrivateFilepath(String userId, String suffix) {
        return String.format("%s/%s/%s_%s.%s",
                SourcePathProperties.PRIVATE_UPLOAD_PATH, getTodayStr(), userId, UUID.randomUUID(), suffix);
    }

    /**
     * 生成公开文件路径
     */
    public static String generatePublicFilepath(String suffix) {
        return String.format("%s/%s/%s.%s", SourcePathProperties.PUBLIC_UPLOAD_PATH, getTodayStr(), UUID.randomUUID(), suffix);
    }

    public static String generatePublicFilepath() {
        return String.format("%s/%s/%s.%s", SourcePathProperties.PUBLIC_UPLOAD_PATH, getTodayStr(), UUID.randomUUID(), "jpg");
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
     * 实际的文件路径 转为客户显示的文件名, 去掉userId, 带上日期子目录.
     */
    public static String getPrivateRealFilepath2Uri(String realFilename) {
        String subStr = realFilename.replace(SourcePathProperties.PRIVATE_UPLOAD_PATH + "/", "");
        String[] strs = subStr.split("/", 2);
        String subDir = strs[0];
        String subFilename = strs[1].replace("^\\d+_", "");
        return subDir + "/" + subFilename;
    }


    public static String uri2PublicFilepath(String filename, ImageSizeEnum sizeEnum) throws CustomException {
        return uri2PrivateFilepath(null, filename, sizeEnum);
    }

    /**
     * @param realpath 如 /User/kidd/tt/private/20180501/fa6cbcbb-bee7-41cd-9235-6bcdd7fd5b57.large.jpg
     * @return 如 20180501/fa6cbcbb-bee7-41cd-9235-6bcdd7fd5b57.large.jpg
     * @throws CustomException
     */
    public static String realFilepath2PublicUri(String realpath) throws CustomException {
        if (!realpath.startsWith(SourcePathProperties.PUBLIC_UPLOAD_PATH)) {
            logger.error("文件: " + realpath);
            logger.error("不匹配路径: " + SourcePathProperties.PUBLIC_UPLOAD_PATH);
            throw new CustomException("内部错误: 参考值10034");
        }
        return realpath.replace(SourcePathProperties.PUBLIC_UPLOAD_PATH + "/", "");
    }

    /**
     * 已登陆用户,传入filename, 取得实际图片的物理路径
     *
     * @param userId
     * @param sizeEnum 文件类型  small, medium, large , origin(默认)
     * @param filename 格式如  20180501/fa6cbcbb-bee7-41cd-9235-6bcdd7fd5b57.jpg
     * @return 如 /User/kidd/tt/public/20180501/13_fa6cbcbb-bee7-41cd-9235-6bcdd7fd5b57.large.jpg
     */
    public static String uri2PrivateFilepath(String userId, String filename, ImageSizeEnum sizeEnum) throws CustomException {
        // 检查 文件名
        if (filename == null || filename.equals("")) {
            throw new CustomException("Illegal filename!");
        }
        Filepath fo = new Filepath(filename);
        if (!fo.isLegal) {
            throw new CustomException("Illegal filename!");
        }
        // 检查 后缀名合法性
        if (!checkFilenameLegal(filename)) {
            throw new CustomException("Illegal filename!");
        }
        // 组合成origin图片地址
        String realPath;
//        if (Strings.isNullOrEmpty(userId)) {
//            // 非授权目录
//            if (sizeEnum == ImageSizeEnum.ORIGIN) {
//                realPath = String.format("%s/%s/%s.%s",
//                        G.PUBLIC_UPLOAD_PATH, fo.path, fo.name, fo.suffix);
//            } else {
//                realPath = String.format("%s/%s/%s.%s.%s",
//                        G.PUBLIC_UPLOAD_PATH, fo.path, fo.name, sizeEnum.getName(), fo.suffix);
//            }
//        } else {
        // 授权目录
        if (sizeEnum == ImageSizeEnum.ORIGIN) {
            realPath = String.format("%s/%s/%s_%s.%s",
                    SourcePathProperties.PRIVATE_UPLOAD_PATH, fo.path, userId, fo.name, fo.suffix);
        } else {
            realPath = String.format("%s/%s/%s_%s.%s.%s",
                    SourcePathProperties.PRIVATE_UPLOAD_PATH, fo.path, userId, fo.name, sizeEnum.getName(), fo.suffix);
        }
//        }
        logger.info((StringUtils.isBlank(userId) ? "非授权" : "授权ID" + userId) + "用户请求下载文件名: " + filename);
        logger.info("组合后文件路径: " + realPath);
        // 检查 文件真实存在
        File file = new File(realPath);
        if (!file.exists() || file.isDirectory()) {
            logger.error("文件不存在: " + realPath);
            throw new CustomException("Illegal filename!");
        }
        return realPath;
    }

    /**
     * 实际物理路径转为用户视图Uri
     *
     * @param realpath 如 /User/kidd/tt/public/20180501/13_fa6cbcbb-bee7-41cd-9235-6bcdd7fd5b57.large.jpg
     * @return 如 20180501/fa6cbcbb-bee7-41cd-9235-6bcdd7fd5b57.jpg
     */
    public static String realFilepath2PrivateUri(String realpath) throws CustomException {
        if (!realpath.startsWith(SourcePathProperties.PRIVATE_UPLOAD_PATH)) {
            logger.error("文件: " + realpath);
            logger.error("不匹配路径: " + SourcePathProperties.PRIVATE_UPLOAD_PATH);
            throw new CustomException("内部错误: 参考值10033");
        }
        String newPath = realpath.replace(SourcePathProperties.PRIVATE_UPLOAD_PATH + "/", "");
        // 去掉userid
        Filepath fp = new Filepath(newPath);
        String[] s = fp.name.split("_", 2);
        if (s.length == 2) {
            return String.format("%s/%s.%s", fp.path, s[1], fp.suffix);
        }
        throw new CustomException("非法文件");
    }


    /**
     * 生成 {width}x{height} 的略缩图
     *
     * @return 新的文件全路径
     */
    public static String resizeImageFile(String realFilepath, ImageSizeEnum imageSize) throws IOException {
        if (imageSize == ImageSizeEnum.ORIGIN) {
            return null;
        }
        Filepath filepath = new Filepath(realFilepath);
        if (filepath.isLegal) {

            String newFilepath = String.format("%s/%s.%s.%s",
                    filepath.path, filepath.name, imageSize.getName(), filepath.suffix);
            File newFile = new File(newFilepath);
            Thumbnails.of(realFilepath).size(imageSize.getWidth(), imageSize.getHeight()).toFile(newFile);
            return newFilepath;
        }
        return null;
    }

//    /**
//     * 压缩图片,尺寸不变  生成 xxx_thumbnail.jpg
//     *
//     * @return 新的文件全路径
//     */
//    public static String zipImageFile(String filepath) throws IOException {
//        FileObject fileObject = new FileObject(filepath);
//        if (fileObject.getLegal()) {
//            String newFilepath = String.format("%s/%s_thumbnail%s",
//                    fileObject.getPath(), fileObject.getName(), fileObject.getSuffix());
//            File newFile = new File(newFilepath);
//            Thumbnails.of(filepath).scale(1f).outputQuality(0.25f).toFile(newFile);
//            return newFilepath;
//        }
//        return "";
//    }
//
//    /**
//     * 加水印
//     * TODO:
//     */
//    public static String watermarkImageFile(String filepath) {
//        return "";
//    }


}
