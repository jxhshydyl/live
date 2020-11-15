package com.ex.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meizan.ancon.common.enumeration.ResultEnum;
import com.meizan.ancon.common.utils.Result;
import com.meizan.ancon.common.vo.ResultVO;
import com.ex.zuul.service.AuthService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 请求url权限校验
 */
@Configuration
public class AccessGatewayFilter implements GlobalFilter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String X_CLIENT_TOKEN_USER = "x-client-token-user";

    private static final String BASIC = "Basic ";

    /**
     * 由authentication-client模块提供签权的feign客户端
     */
    @Resource
    private AuthService authService;

    /**
     * security 账号密码
     */
    @Value("${spring.security.user.name}")
    private String securityName;

    /**
     * security 账号密码
     */
    @Value("${spring.security.user.password}")
    private String securityPassword;

    /**
     * 1.首先网关检查token是否有效，无效直接返回401，不调用签权服务
     * 2.调用签权服务器看是否对该请求有权限，有权限进入下一个filter，没有权限返回401
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String method = request.getMethodValue();
        String url = request.getPath().value();
        ServerHttpRequest.Builder builder = request.mutate();
        log.debug("url:{},method:{},headers:{}", url, method, request.getHeaders());
        //不需要网关签权的url
        if (!authService.needAuthentication(url)) {
            log.info("不需要鉴权:" + url);
            builder.header("Authorization", getSecurityAccount());
            return chain.filter(exchange);
        }
        //调用签权服务看用户是否有权限，若有权限进入下一个filter
        if (authService.hasPermission(authentication)) {
            //将jwt token中的用户信息传给服务
            log.info("X_CLIENT_TOKEN_USER={}", getUserToken(authentication));
            builder.header(X_CLIENT_TOKEN_USER, getUserToken(authentication));
            return chain.filter(exchange.mutate().request(builder.build()).build());
        }
        return unauthorized(exchange);
    }

    /**
     * 提取jwt token中的数据，转为json
     *
     * @param authentication
     * @return
     */
    private String getUserToken(String authentication) {
        String token = "{}";
        try {
            token = new ObjectMapper().writeValueAsString(authService.getJwt(authentication).getBody());
            return token;
        } catch (JsonProcessingException e) {
            log.error("token json error:{}", e.getMessage());
        }
        return token;
    }

    private String getSecurityAccount() {
        return BASIC + Base64.encodeBase64String((securityName + ":" + securityPassword).getBytes());
    }

    /**
     * 网关拒绝，返回401
     *
     * @param
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        ResultVO resultVO = Result.error(ResultEnum.unauthorized.getCode());
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(JSON.toJSONString(resultVO).getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }
}
