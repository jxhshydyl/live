/**
 * @Title: SessionUser.java
 * @Package com.yijiu.eotc.portal.entities
 * @Description:
 * @author vDalf   2020年5月8日 下午4:38:07
 */
package com.ex.user.model.vo;

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
