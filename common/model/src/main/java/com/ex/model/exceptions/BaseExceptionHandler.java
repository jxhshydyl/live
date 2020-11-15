package com.ex.model.exceptions;

import com.ex.model.enums.ResultEnum;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResultVO handlerBusinessException(BusinessException e) {
        log.error("异常处理:", e);
        return Result.error(e.getErrCode(), e.getErrMsg());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultVO MethodArgumentNotValidHandler(HttpServletRequest request,
                                                  MethodArgumentNotValidException exception) throws Exception {
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        log.error("异常处理:", exception);
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            return Result.error(ResultEnum.FAIL.getCode(), error.getDefaultMessage());
        }
        return Result.error(ResultEnum.PARAM_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResultVO handlerException(Exception e) {
        log.error("异常处理:", e);
        return Result.error(ResultEnum.FAIL.getCode(), e.getMessage());
    }

}