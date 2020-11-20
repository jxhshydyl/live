/**
 * @Title: SessionUser.java
 * @Package com.yijiu.eotc.portal.entities
 * @Description:
 */
package com.ex.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: SessionUser
 * @Description: 单点登陆会话信息<br>
 */
@Data
public class SessionUser {
    public String token;
    public Long userId;
    public String userType;
    public String userName;
    public LocalDateTime loginTime;
    public String loginIp;
    public Long lastActivityTime;

}
