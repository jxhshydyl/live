package com.ex.model.exceptions;

import com.ex.model.enums.ResultEnum;

/**
 * @ClassName: BusinessException
 * @Description:
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -1446547266526417221L;

    private Integer errCode;
    private String errMsg;
    private Exception e;

    public BusinessException(String errMsg) {
        this(ResultEnum.FAIL.getCode(), errMsg, null);
    }

    public BusinessException(ResultEnum resultEnum) {
        this(resultEnum, null);
    }

    public BusinessException(ResultEnum resultEnum, Exception e) {
        this(resultEnum.getCode(), resultEnum.getMsg(), e);
    }

    public BusinessException(Integer code, String errMsg) {
        this(code, errMsg, null);
    }

    public BusinessException(Integer errCode, String errMsg, Exception e) {
        super(errMsg, e);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.e = e;
    }


    public static BusinessException fail(String errMsg) {
        return new BusinessException(ResultEnum.FAIL.getCode(), errMsg);
    }

    public static BusinessException fail(String errMsg, Exception e) {
        return new BusinessException(ResultEnum.FAIL.getCode(), errMsg, e);
    }

    public static BusinessException fail(Integer code, String errMsg, Exception e) {
        return new BusinessException(code, errMsg, e);
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
