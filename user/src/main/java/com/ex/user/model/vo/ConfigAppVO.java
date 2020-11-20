package com.ex.user.model.vo;

import lombok.Data;

/**
 * @Date: 20/11/2020 下午 5:44
 * @Version: 1.0
 * @Description:
 */
@Data
public class ConfigAppVO {
    /**
     * id
     */
    private Integer id;
    /**
     * 客户端(android,ios)
     */
    private String client;
    /**
     * 版本号
     */
    private String version;
    /**
     * 内部版本号
     */
    private Integer build;
    /**
     * 下载URL
     */
    private String url;
    /**
     * 更新内容
     */
    private String description;
    /**
     * en更新内容
     */
    private String enDescription;
    /**
     * 是否强制升级  1：是  0：否
     */
    private Integer remind;
    /**
     * downloadUrl
     */
    private String downloadUrl;
}
