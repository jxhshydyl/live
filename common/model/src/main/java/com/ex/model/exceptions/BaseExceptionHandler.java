package com.ex.model.exceptions;

import com.ex.model.enums.ResultEnum;
import com.ex.model.vo.Result;
import com.ex.model.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseExceptionHandler {


    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultVO handlerBusinessException(BusinessException e) {
        e.printStackTrace();
        return Result.error(e.getErrCode(), e.getErrMsg());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO handlerException(Exception e) {
        e.printStackTrace();
        return Result.error(ResultEnum.FAIL.getCode(), e.getMessage());
    }

}