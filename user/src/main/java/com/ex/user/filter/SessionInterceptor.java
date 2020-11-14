/**
 * @Title: SessionFilter.java
 * @Package com.yijiu.eotc.portal.filter
 * @Description:
 */
package com.ex.user.filter;

import com.alibaba.fastjson.JSON;
import com.ex.model.constant.RedisKeyConstant;
import com.ex.model.enums.ResultEnum;
import com.ex.model.vo.Result;
import com.ex.user.model.vo.SessionUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @ClassName: SessionFilter
 * @Description: 单点登录拦截器
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String X_CLIENT_TOKEN_USER = "x-client-token-user";

    Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(X_CLIENT_TOKEN_USER);
        if (!StringUtils.isEmpty(token)) {
            SessionUser session = (SessionUser) redisTemplate.opsForValue().get(RedisKeyConstant.SSO_SESSION + token);
            if (session == null) {
                logger.error("preHandle,鉴权失败，session为空,token:{}", token);
                reject(response);
                return false;
            }
        } else {
            logger.error("preHandle,鉴权失败，token为空");
            reject(response);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void reject(HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"), true);) {
            String jsonStr = JSON.toJSONString(Result.error(ResultEnum.USER_NOT_LOGIN));
            writer.write(jsonStr);
            writer.flush();
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
            logger.error("请求处理失败信息响应失败,IO异常 :", e);
        }
    }

}
