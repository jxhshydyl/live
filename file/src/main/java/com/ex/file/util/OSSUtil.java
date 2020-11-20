package com.ex.file.util;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ex.file.config.AliOssProperties;
import com.ex.model.enums.ResultEnum;
import com.ex.model.exceptions.BusinessException;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import com.ex.util.DateUtils;
import com.ex.util.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: FileService
 * @Description:
 */
@ConditionalOnClass(OSS.class)
@Service
@EnableConfigurationProperties(AliOssProperties.class)
public class OSSUtil {

    private static final Logger logger = LoggerFactory.getLogger(OSSUtil.class);

    private String website = "imotc.geminitoken.vip";

    private static final int FONT_SIZE = 24;

    private static float ALPHA = 0.5f;

    private static Font font = new Font("微软雅黑", Font.BOLD, FONT_SIZE);

    @Autowired
    private AliOssProperties aliOssProperties;

    private ClientBuilderConfiguration ossClientConfig;

    @PostConstruct
    public void initOss() {
        ossClientConfig = new ClientBuilderConfiguration();
        ossClientConfig.setMaxConnections(aliOssProperties.getMaxConnections());
        ossClientConfig.setConnectionRequestTimeout(aliOssProperties.getConnectionRequestTimeout());
        ossClientConfig.setConnectionTimeout(aliOssProperties.getConnectionTimeout());
        ossClientConfig.setRequestTimeoutEnabled(aliOssProperties.isRequestTimeoutEnabled());
        ossClientConfig.setRequestTimeout(aliOssProperties.getRequestTimeout());
    }

    private OSS getOssClient() {
        return new OSSClientBuilder().build(aliOssProperties.getEndpoint(), aliOssProperties.getAccessKeyId(), aliOssProperties.getAccessKeySecret(), ossClientConfig);
    }

    /**
     * 1文件上传<br>
     * 1大小限制<br>
     * 1 图片加水印<br>
     * 1文件名能区分用户<br>
     * 1文件名体现时间戳<br>
     */
    public ResultVO fileUpload(MultipartFile uploadFile, boolean watermark) {
        String userId = "";
//        EnumRequestSource source = EnumRequestSource.getByCode(RequestContext.getEnv().getSource());
        String fileName =
                userId + "_" + UUID.randomUUID().toString() + RandomUtils.getRandomWord(6).toUpperCase();
        String suffix = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf(".") + 1);
        String contentType = uploadFile.getContentType();
        if (contentType == null) {
            throw new BusinessException(ResultEnum.PARAM_ERROR);
        }
        try (InputStream inputStream = uploadFile.getInputStream();) {
            String url;
            //a图片加水印
            try (InputStream in = watermark(inputStream, suffix);) {
                url = this.uploadOss(in, fileName, suffix);
            } catch (Exception e) {
                throw e;
            }
//            url = this.uploadOss(inputStream, fileName, suffix);
            logger.info("文件上传成功:[{}]", url);
            URI uri = URI.create(url);
            return Result.success(uri.getPath());
        } catch (IOException e) {
            logger.error("e---->", e);
            return Result.error(ResultEnum.FILE_UPLOAD_ERROR);
        }
    }

    /**
     * 1文件上传<br>
     * 1大小限制<br>
     * 1 图片加水印<br>
     * 1文件名能区分用户<br>
     * 1文件名体现时间戳<br>
     */
    public ResultVO fileUpload(MultipartFile uploadFile, String userId, String partner) {
        logger.info("用户[{}]上传文件[{}]开始....", userId, uploadFile.getOriginalFilename());
        String fileName =
                partner + "_" + userId + "_" + UUID.randomUUID().toString() + RandomUtils.getRandomWord(6).toUpperCase();
        String suffix = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf(".") + 1);
        String contentType = uploadFile.getContentType();
        if (contentType == null) {
            throw new BusinessException(ResultEnum.PARAM_ERROR);
        }
        try (InputStream inputStream = uploadFile.getInputStream();) {
            String url;
            url = this.uploadOss(inputStream, fileName, suffix);
            logger.info("文件上传成功:[{}]", url);
            URI uri = URI.create(url);
            return Result.success(uri.getPath());
        } catch (IOException e) {
            logger.error("e---->", e);
            return Result.error(ResultEnum.FILE_UPLOAD_ERROR);
        }
    }

    private String uploadOss(InputStream inputStream, String fileName, String suffix) {
        logger.info("upload start.....");

        OSS ossClient = this.getOssClient();
        try {
            String key = fileName + "." + suffix;
//			if(true) {
//				OutputStream out = new FileOutputStream("D:\\temp\\data/"+key);
//				int ch;
//		        while ((ch = inputStream.read()) != -1) {   
//		        	out.write(ch);   
//		        }
//		        out.close();
//		        return key;
//			}
            ossClient.putObject(aliOssProperties.getBucket(), key, inputStream);
            logger.info("key--->{}", key);
            URL url = ossClient.generatePresignedUrl(aliOssProperties.getBucket(), key, this.getExpiration());
            return url.toString();
        } catch (Exception e) {
            logger.error("upload to oss error", e);
            throw new BusinessException(ResultEnum.FILE_UPLOAD_ERROR);
        } finally {
            ossClient.shutdown();
        }
    }

    /**
     * 1文件有效期默认3年
     */
    private Date getExpiration() {
        return DateUtils.localDateTime2Date(LocalDateTime.now().plusYears(5));
    }

    /**
     * a图片流加水印
     */
    public InputStream watermark(InputStream inputStream, String suffix) throws IOException {
        Image img = ImageIO.read(inputStream);
        int imgWidth = img.getWidth(null);
        int imgHeight = img.getHeight(null);
        BufferedImage bufImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufImg.createGraphics();
        graphics2D.drawImage(img, 0, 0, imgWidth, imgHeight, null);
        graphics2D.setFont(font);
        graphics2D.setColor(Color.getHSBColor(223, 3, 82));
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, ALPHA));
        Integer degree = -45;
        graphics2D.rotate(Math.toRadians(degree), (double) bufImg.getWidth() / 2, (double) bufImg.getHeight() / 2);
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        Rectangle rec = font.getStringBounds(website, frc).getBounds();
        double fontWidth = rec.getWidth();
        double fontHeight = rec.getHeight();
        int space = imgHeight / FONT_SIZE;
        for (int index = 0; index <= imgWidth / fontWidth; index++) {
            int x = (int) (index * fontWidth);
            int y = 0;
            if ((index & 1) == 0) {
                y = (int) fontHeight;
            }
            for (int i = 0; i < space; i++) {
                graphics2D.drawString(website, x, y);
                y += (12 * FONT_SIZE);
            }
        }
        graphics2D.dispose();
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
        ImageIO.write(bufImg, suffix, imOut);
        return new ByteArrayInputStream(bs.toByteArray());
    }

    public String getFileDownloadHost() {
        return aliOssProperties.getFileDownloadHost();
    }
}
