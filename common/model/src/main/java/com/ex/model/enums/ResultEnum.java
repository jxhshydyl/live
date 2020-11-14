package com.ex.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum ResultEnum {
    /**base*/
    FAIL(40100, "操作失败"),

    /**user*/
    USER_NOT(10000,"用户不存在"),
    USER_NAME_EXISTS(10001,"用户名已存在"),
    USER_AUTH_PASS(10002,"用户已经实名认证"),
    USER_CART_NUMBER_USED(10003,"证件号已被认证"),
    MESSAGE_TYPE_ERROR(10004,"短信验证码类型错误"),
    MOBILE_USED(10005,"手机号已被使用"),
    USER_SUB_CODE_FAIL(10006,"生成订阅码失败"),
    USER_NOT_SUB_CODE(10007,"用户未生成该订阅码"),
    USER_PASSWORD_INPUT_ERROR(10008,"用户密码输入错误次数过多"),
    USER_NOT_LOGIN(10009,"用户未登录"),

    needImgCode(40101, "请输入正确的图形验证码"),
    needGoogleAuth(40102, "需要Google验证"),
    googleAuthFail(40121, "Google验证码错误"),
    needLoginAuth(40103, "需要异地登录验证"),
    needLoginAndGoogleAuth(40104, "需要异地登录和Google验证"),
    noWalletAddress(40105, "钱包没有地址"),
    notEnoughMoney(40106, "操作失败，没有足够的资金"),
    faildPromError(40107, "操作失败，请稍后再试"/*未知异常*/),
    cancleFaildPromError(40108, "系统处理中"/*"撤单失败，未知异常请稍后再试"*/),
    /************* 鉴权相关的返回定义 ***************/
    invalidRequest(40109, "无效请求"),
    invalidClient(40110, "无效client_id"),
    invalidGrant(40111, "无效授权"),
    invalidScope(40112, "无效scope"),
    invalidToken(40113, "无效token"),
    insufficientScope(40114, "授权不足"),
    errorPassword(40115, "用户名或密码错误"),
    redirectUriMismatch(40120, "redirect url不匹配"),
    accessDenied(40130, "拒绝访问"),
    methodNotAllowed(40140, "不支持该方法"),
    ACCOUNT_LOCKED(40141, "账户已锁定"),
    unauthorizedClient(40160, "未授权客户端"),

    unauthorized(40161, "未授权"),
    unsupported_response_type(40170, " 支持的响应类型"),
    unsupported_grant_type(40171, "不支持的授权类型"),
    needSetSafePwd(41101, "需要设置安全密码"),
    systemError(40400, "系统繁忙，请稍后再试"),
    gatewayNotFound(40401, "服务未找到"),
    wrongParam(40601, "参数错误"),

    serverError(50001, "系统错误"),
    serverFallback(50002, "服务异常"),
    gatewayError(50003, "网关异常"),
    serverInternalError(50004, "系统内部错误"),
    gatewayConnectTimeOut(50004, "网关超时"),
    alreadyExists(40409, "Parameter already exists"),
    invalidCoin(40180, "无效币种"),
    marketAlreadyExists(223100, "市场已存在"),
    coinAlreadyExists(223101, "币种已存在"),
    sysUserAlreadyExists(223102, "用户已存在"),
    /*base*/
    success(200, "成功"),
    /*news*/
    NEWS_ADD_FAIL(70010, " 新增公告失败"),
    NEWS_UPDATE_FAIL(70011, " 更新公告失败"),
    NEWS_DEL_FAIL(70012, " 删除公告失败"),
    /*app version*/
    VERSION_ADD_FAIL(70020, "新增APP版本失败"),
    VERSION_UPDATE_FAIL(70021, "更新APP版本失败"),
    VERSION_DEL_FAIL(70022, "删除APP版本失败"),
    VERSION_CLIENT_EXIST(70023, "版本类型已存在"),
    VERSION_GET_FAIL(70024, "获取版本信息失败"),


    /*withdraw*/
    WITHDRAW_UPDATE_FAIL(70030, "提币审核失败"),
    WITHDRAW_FORBID_OPERATE(70031, "提币状态错误"),
    WITHDRAW_ORDER_NOT_EXIST(70032, "提币记录不存在"),
    /*sysConfig*/
    SYS_CONFIG_ADD_FAIL(70033, "系统配置添加失败"),
    SYS_CONFIG_UPDATE_FAIL(70034, "系统配置更新失败"),
    ;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static Map<Integer, String> resultMap;

    public static String getMessage(int code) {
        if (resultMap == null) {
            resultMap = new HashMap<>();
            for (ResultEnum resultEnum : ResultEnum.values()) {
                resultMap.put(resultEnum.code, resultEnum.msg);
            }
        }
        String message = resultMap.getOrDefault(code, "System Error");
        return message;
    }

}
