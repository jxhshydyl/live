package com.ex.message.spring.config;

/**
 * @Date: 12/6/2020 上午 11:59
 * @Version: 1.0
 * @Description: 美联配置
 */
public class MLSmsProperties {
    private String domain;
    private String smsApiUser;
    private String smsApiKey;
    private String smsApiPassword;
    private String smsApiUrl;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSmsApiUser() {
        return smsApiUser;
    }

    public void setSmsApiUser(String smsApiUser) {
        this.smsApiUser = smsApiUser;
    }

    public String getSmsApiKey() {
        return smsApiKey;
    }

    public void setSmsApiKey(String smsApiKey) {
        this.smsApiKey = smsApiKey;
    }

    public String getSmsApiPassword() {
        return smsApiPassword;
    }

    public void setSmsApiPassword(String smsApiPassword) {
        this.smsApiPassword = smsApiPassword;
    }

    public String getSmsApiUrl() {
        return smsApiUrl;
    }

    public void setSmsApiUrl(String smsApiUrl) {
        this.smsApiUrl = smsApiUrl;
    }

}
