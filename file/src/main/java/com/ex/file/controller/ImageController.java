package com.ex.file.controller;

import com.ex.file.enums.ImageSizeEnum;
import com.ex.file.exception.CustomException;
import com.ex.file.config.SourcePathProperties;
import com.ex.file.service.ImageFileService;
import com.ex.file.task.ImageCoverTask;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * <p>
 * 功能:
 * 上传, 访问图片文件
 * 限制:
 * 所有请求 首先验证用户已经登录
 * 用户只能访问自己上传的图片.
 * 说明:
 * 上传图片子目录以日期划分, 文件名与用户ID组合, 以便用户获取图片时核对用户ID
 * <p>
 * 用户GET图片名格式:   20180501/{uuid}.jpg
 * 实际保存图片名格式:   20180501/{userId}_{uuid}.jpg
 * <p>
 * TODO: 考虑用异步模式, 提高访问图片的处理能力.
 */

@RestController
@RequestMapping("/file/api/{device}/{version}")
public class ImageController extends BaseController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    private static boolean checkRefererAvailable(String referer) {
        if (referer == null || referer.equals("")) {
            return false;
        }
        boolean isAvailable = true;
        if (!referer.matches("^https?://[^.]+\\." + "".toLowerCase() + ".*")) {
            isAvailable = false;
        }
        String s = "";
        String[] split = s.split(",");
        if (split != null || split.length > 0) {
            for (String s1 : split) {
                if (referer.matches("^https?://[^.]+\\." + s1.toLowerCase() + ".*")) {
                    isAvailable = true;
                }
            }
        }
        return isAvailable;
    }

    // 公开的图片上传服务, 存放目录与鉴权上传的目录分开,
    // 图片获取使用nginx代理(nginx必须设置max_clinet_body限制上传文件大小)
    @PostMapping(value = "/image/public/upload")
    @ResponseBody
    public ResultVO uploadImagePublic() {
        // 过滤来源
        if (!checkRefererAvailable(request.getHeader("Referer"))) {
            return Result.error(0, "domain not allow");
        }
        try {
            String filename = ImageFileService.generatePublicFilepath();
            ImageFileService.receiveUploadImage(request, filename);
            // 丢到image cover queue
            ImageCoverTask.addImage(filename);
            // 转为uri丢给用户
            String s = ImageFileService.realFilepath2PublicUri(filename);
            return Result.success(s);
        } catch (CustomException e) {
            return Result.error(0, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return Result.error(0, "上传失败");
    }

    // 非公开的图片上传服务, 必须鉴权才可上传, nginx不代理目录 (nginx必须设置max_clinet_body限制上传文件大小)
    @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
    @PostMapping(value = "/image/upload")
    @ResponseBody
    public ResultVO uploadImagePrivate() {
        // 过滤来源
        if (!checkRefererAvailable(request.getHeader("Referer"))) {
            return Result.error(0, "domain not allow");
        }
        // 要求登陆
        Long userId = null;
        try {
            String filename = ImageFileService.generatePrivateFilepath(userId + "");
            ImageFileService.receiveUploadImage(request, filename);
            // 丢到image cover queue
            ImageCoverTask.addImage(filename);
            // 转为uri丢给用户
            String s = ImageFileService.realFilepath2PrivateUri(filename);
            return Result.success(s);
        } catch (CustomException e) {
            return Result.error(0, e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return Result.error(0, "上传失败");
    }


    /**
     * 只给普通用户获取图片
     * <p>
     * 用户登录权限           √
     * 判断文件字符           √
     * 判断文件有效性         √
     * 判断用户对文件的权限    √
     * 返回字节流             √
     */
    @GetMapping(value = "/image/get")
    public void getPrivateImage(@RequestParam("size") String size,
                                @RequestParam("filename") String filename) throws IOException {
        //登录
        String userId = null;
        // 取得图片类型
        ImageSizeEnum sizeEnum = ImageFileService.getImageSizeEnum(size);
        String filepath = null;
        try {
            String realFilepath = ImageFileService.uri2PrivateFilepath(userId, filename, sizeEnum);
            if (realFilepath == null) {
                filepath = SourcePathProperties.DEFAULT_404_IMAGE;
                response.setContentType("image/jpeg");
                response.setHeader("Cache-Control", "max-age=604800"); //设置缓存
            } else {
                filepath = realFilepath;
                response.setContentType("image/jpeg");
                response.setHeader("Cache-Control", "max-age=604800"); //设置缓存
            }

            FileInputStream inputStream = new FileInputStream(filepath);
            OutputStream outputStream = response.getOutputStream();

            byte[] bts = new byte[8192];
            int len = -1;
            while ((len = inputStream.read(bts)) != -1) {
                outputStream.write(bts, 0, len);
            }
            inputStream.close();
            outputStream.flush();
        } catch (CustomException e) {
            response.setContentType("text/plain;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(e.getMessage());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("Internal Error!");
        writer.flush();
        writer.close();
    }

}
