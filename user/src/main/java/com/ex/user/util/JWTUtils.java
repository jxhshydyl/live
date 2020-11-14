package com.ex.user.util;

import com.ex.model.constant.Constants;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @ClassName: JWTUtils
 * @Description:
 */
public class JWTUtils {

    private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);

    private static final String ISSUER = "atop.yijiu.com";
    private static final String SECRET = "HyFJUkNX3Tmwggxy/mfXdw2fMw+Ifns3sk605lawDA0=";
    private static final String DEFAULT_SUBJECT = "UNKNOW";

    /**
     * 生成Token
     *
     * @param userId   用户id
     * @param username 用户名
     * @return token String
     */
    public static String createJwtToken(Long userId, String username) {
        return createJwtToken(userId, username, DEFAULT_SUBJECT);
    }

    /**
     * 生成Token
     *
     * @param userId   用户id
     * @param userName 用户名
     * @param subject  该JWT所面向的用户，是否使用是可选的
     * @return token String
     */
    public static String createJwtToken(Long userId, String userName, String subject) {
        return createJwtToken(userId, userName, subject, Constants.JWT_LOGIN_TIME);
    }


    /**
     * 生成Token
     *
     * @param userId    用户id
     * @param userName    该JWT的签发者，是否使用是可选的
     * @param subject   该JWT所面向的用户，是否使用是可选的
     * @param ttlMillis 有效期  单位:毫秒
     * @return token String
     */
    public static String createJwtToken(Long userId, String userName, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setId(String.valueOf(userId)).setIssuedAt(now).setSubject(subject).setIssuer(ISSUER).signWith(signatureAlgorithm, signingKey).claim("userName", userName);
        if (ttlMillis >= 0) {
            builder.setExpiration(new Date(nowMillis + ttlMillis));
        }
        return builder.compact();
    }

    /**
     * @return null:未登录或登录超时
     * @Description: 解析JWT
     */
    public static Claims parseJWT(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET)).parseClaimsJws(jwt).getBody();
            claims.get("sub", String.class);
            return claims;
        } catch (ExpiredJwtException e) {
            logger.error("解析JWT失败:token已过期");
            return null;
        } catch (SignatureException e) {
            logger.error("解析JWT失败:签名不匹配");
            return null;
        } catch (Exception e) {
            logger.error("解析JWT失败:", e);
            return null;
        }
    }

}
