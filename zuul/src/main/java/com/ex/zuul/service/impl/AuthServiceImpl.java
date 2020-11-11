package com.ex.zuul.service.impl;

import com.meizan.ancon.common.vo.ResultVO;
import com.ex.zuul.provider.AuthProvider;
import com.ex.zuul.service.AuthService;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class AuthServiceImpl implements AuthService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Authorization认证开头是"bearer "
     */
    private static final String BEARER = "Bearer ";

    @Autowired
    private AuthProvider authProvider;

    /**
     * jwt token 密钥，主要用于token解析，签名验证
     */
    @Value("${spring.security.oauth2.jwt.signingKey}")
    private String signingKey;

    /**
     * 不需要网关签权的url配置(/auth,/open)
     * 默认/oauth开头是不需要的
     */
    @Value("${gate.ignore.authentication.startWith}")
    private String ignoreUrls;

    @Value("${gate.ignore.authentication.need}")
    private String needUrls;

    @Override
    public ResultVO authenticate(String authentication, String url, String method) {
        return authProvider.auth(authentication, url, method);
    }

    @Override
    public boolean ignoreAuthentication(String url) {
        return Stream.of(this.ignoreUrls.split(",")).anyMatch(ignoreUrl -> url.indexOf(StringUtils.trim(ignoreUrl)) >= 0);
    }

    @Override
    public boolean needAuthentication(String url) {
        return Stream.of(this.needUrls.split(",")).anyMatch(ignoreUrl -> url.indexOf(StringUtils.trim(ignoreUrl)) >= 0);
    }

    @Override
    public boolean hasPermission(ResultVO authResult) {
        log.debug("签权结果:{}", authResult.getData());
        return authResult.getStatus() == 200 && (boolean) authResult.getData();
    }

    @Override
    public boolean hasPermission(String authentication) {
        // 如果请求未携带token信息, 直接权限
        if (StringUtils.isBlank(authentication) || !authentication.startsWith(BEARER)) {
            log.error("user token is null");
            return Boolean.FALSE;
        }
        //token是否有效，在网关进行校验，无效/过期等
        if (invalidJwtAccessToken(authentication)) {
            return Boolean.FALSE;
        }
        return true;
    }

    @Override
    public Jws<Claims> getJwt(String jwtToken) {
        if (jwtToken.startsWith(BEARER)) {
            jwtToken = StringUtils.substring(jwtToken, BEARER.length());
        }
        return Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(signingKey.getBytes()) //设置签名的秘钥
                .parseClaimsJws(jwtToken);
    }

    @Override
    public boolean invalidJwtAccessToken(String authentication) {
        // 是否无效true表示无效
        boolean invalid = Boolean.TRUE;
        try {
            getJwt(authentication);
            invalid = Boolean.FALSE;
        } catch (SignatureException | ExpiredJwtException | MalformedJwtException ex) {
            log.error("user token error :{}", ex.getMessage());
        }
        return invalid;
    }
}
