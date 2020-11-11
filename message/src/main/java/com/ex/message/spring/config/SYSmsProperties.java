package com.ex.message.spring.config;

/**
 * @Date: 12/6/2020 下午 12:00
 * @Version: 1.0
 * @Description: 赛邮配置
 */
public class SYSmsProperties {
    private String smsSyAppId;
    private String smsSySignature;
    private String smsSyApiUrl;
    /**
     * 最大失败次数
     */
    public Integer maxFailResendTime;

    public String getSmsSyAppId() {
        return smsSyAppId;
    }

    public void setSmsSyAppId(String smsSyAppId) {
        this.smsSyAppId = smsSyAppId;
    }

    public String getSmsSySignature() {
        return smsSySignature;
    }

    public void setSmsSySignature(String smsSySignature) {
        this.smsSySignature = smsSySignature;
    }

    public String getSmsSyApiUrl() {
        return smsSyApiUrl;
    }

    public void setSmsSyApiUrl(String smsSyApiUrl) {
        this.smsSyApiUrl = smsSyApiUrl;
    }

    public Integer getMaxFailResendTime() {
        return maxFailResendTime;
    }

    public void setMaxFailResendTime(Integer maxFailResendTime) {
        this.maxFailResendTime = maxFailResendTime;
    }
}
