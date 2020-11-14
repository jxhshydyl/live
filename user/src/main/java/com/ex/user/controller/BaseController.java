package com.ex.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
        JSONObject tokenUser = JSON.parseObject(headerStr);
        return tokenUser.getLongValue("uid");
    }
}
