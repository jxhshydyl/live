package com.ex.user.controller;

import com.ex.model.constant.RedisKeyConstant;
import com.ex.model.enums.ResultEnum;
import com.ex.model.exceptions.BusinessException;
import com.ex.user.model.vo.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String X_CLIENT_TOKEN_USER = "x-client-token-user";

    protected Long getUid() {
        String headerStr = request.getHeader(X_CLIENT_TOKEN_USER);
        SessionUser session = (SessionUser) redisTemplate.opsForValue().get(RedisKeyConstant.SSO_SESSION + headerStr);
        if (session == null) {
            throw new BusinessException(ResultEnum.USER_NOT_LOGIN);
        }
        return session.getUserId();
    }
}
