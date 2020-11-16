package com.ex.zuul.exception;

import com.ex.model.enums.ResultEnum;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.netty.channel.ConnectTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@Component
public class GateWayExceptionHandlerAdvice {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResultVO handle(ResponseStatusException ex) {
        log.error("response status exception:{}", ex.getMessage());
        return Result.error(ResultEnum.gatewayError.getCode());
    }

    @ExceptionHandler(value = {ConnectTimeoutException.class})
    public ResultVO handle(ConnectTimeoutException ex) {
        log.error("connect timeout exception:{}", ex.getMessage());
        return Result.error(ResultEnum.gatewayConnectTimeOut.getCode());
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultVO handle(NotFoundException ex) {
        log.error("not found exception:{}", ex.getMessage());
        return Result.error(ResultEnum.gatewayNotFound.getCode());
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultVO handle(ExpiredJwtException ex) {
        log.error("ExpiredJwtException:{}", ex.getMessage());
        return Result.error(ResultEnum.invalidToken.getCode());
    }

    @ExceptionHandler(value = {SignatureException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultVO handle(SignatureException ex) {
        log.error("SignatureException:{}", ex.getMessage());
        return Result.error(ResultEnum.invalidToken.getCode());
    }

    @ExceptionHandler(value = {MalformedJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultVO handle(MalformedJwtException ex) {
        log.error("MalformedJwtException:{}", ex.getMessage());
        return Result.error(ResultEnum.invalidToken.getCode());
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO handle(RuntimeException ex) {
        log.error("runtime exception:{}", ex.getMessage());
        return Result.error(ResultEnum.systemError.getCode());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO handle(Exception ex) {
        log.error("exception:{}", ex.getMessage());
        return Result.error(ResultEnum.systemError.getCode());
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO handle(Throwable throwable) {
        ResultVO result = new ResultVO();
        if (throwable instanceof ResponseStatusException) {
            result = handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            result = handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof NotFoundException) {
            result = handle((NotFoundException) throwable);
        } else if (throwable instanceof RuntimeException) {
            result = handle((RuntimeException) throwable);
        } else if (throwable instanceof Exception) {
            result = handle((Exception) throwable);
        }
        return result;
    }
}
