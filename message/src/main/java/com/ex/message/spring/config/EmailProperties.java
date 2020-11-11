package com.ex.message.spring.config;

/**
 * @Date: 12/6/2020 上午 11:51
 * @Version: 1.0
 * @Description:
 */
public class EmailProperties {
    private String emailFrom;
    private String emailNickName;
    private String emailUserName;
    private String emailPassword;
    private String emailProtocol;
    private String emailHost;
    private String emailPort;
    private boolean emailSslEnable;

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailNickName() {
        return emailNickName;
    }

    public void setEmailNickName(String emailNickName) {
        this.emailNickName = emailNickName;
    }

    public String getEmailUserName() {
        return emailUserName;
    }

    public void setEmailUserName(String emailUserName) {
        this.emailUserName = emailUserName;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getEmailProtocol() {
        return emailProtocol;
    }

    public void setEmailProtocol(String emailProtocol) {
        this.emailProtocol = emailProtocol;
    }

    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public String getEmailPort() {
        return emailPort;
    }

    public void setEmailPort(String emailPort) {
        this.emailPort = emailPort;
    }

    public boolean getEmailSslEnable() {
        return emailSslEnable;
    }

    public void setEmailSslEnable(boolean emailSslEnable) {
        this.emailSslEnable = emailSslEnable;
    }
}
