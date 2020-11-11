package com.ex.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum ResultEnum {

    /**user*/
    USER_NAME_EXISTS(10001,"用户名已存在"),
    fail(40100, "操作失败"),
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
    /*wallet*/
    WALLET_ADDRESS_FAIL(60000, "获取充币地址失败"),
    WITHDRAW_ADDRESS_ERROR(60001, "提币地址错误"),
    RECHARGE_NOT_OPEN(60002, "充值暂未开放"),
    WITHDRAW_NOT_OPEN(60003, "提币暂未开放"),
    WITHDRAW_AMOUNT_EXC(60004, "提币金额异常"),
    NO_BALANCE(60005, "余额不足"),
    WITHDRAW_FEE_ERROR(60006, "提币手续费错误"),
    WITHDRAW_AMOUNT_GT_FEE(60007, "提币金额应大于手续费"),
    WITHDRAW_ADDRESS_NON(60008, "提币地址不存在"),
    ASSET_TYPE_ERROR(60009, "资金账户类型错误"),
    TRANSFER_AMOUNT_ERROR(60010, "划转金额错误"),
    BILL_DEAL_FAIL(60011, "资金账单处理失败"),
    TRANSFER_FAIL(60012, "划转失败"),
    COIN_INFO_EXC(60013, " 币种信息异常"),
    INSERT_DEPOSIT_FAIL(60014, "写入充值订单失败"),
    UPDATE_DEPOSIT_FAIL(60015, "更新充值订单失败"),
    CHAIN_INFO_EXC(60016, " 链信息异常"),
    UPDATE_ORDER_STATUS_FAIL(60018, "修改订单状态失败"),
    WITHDRAW_ADDRESS_ILLEGAL(60019, "提币地址不合法"),
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
    /*coins defi apply*/
    DEFI_ADD_FAIL(70025, "新增DEFI申请失败"),
    DEFI_UPDATE_FAIL(70026, "更新DEFI申请失败"),
    DEFI_DEL_FAIL(70027, "删除DEFI申请失败"),
    DEFI_GET_FAIL(70028, "获取DEFI申请失败"),
    DEFI_COIN_EXIST(70029, "DEFI申请币种已存在"),

    /*withdraw*/
    WITHDRAW_UPDATE_FAIL(70030, "提币审核失败"),
    WITHDRAW_FORBID_OPERATE(70031, "提币状态错误"),
    WITHDRAW_ORDER_NOT_EXIST(70032, "提币记录不存在"),
    /*sysConfig*/
    SYS_CONFIG_ADD_FAIL(70033, "系统配置添加失败"),
    SYS_CONFIG_UPDATE_FAIL(70034, "系统配置更新失败"),
    /*oreConfig*/
    ORE_CONFIG_ADD_FAIL(70035, "ORE配置添加失败"),
    ORE_CONFIG_UPDATE_FAIL(70036, "ORE配置更新失败"),
    ORE_CONFIG_DEL_FAIL(70037, "ORE配置删除失败"),
    ORE_CONFIG_EXIST(70038, "ORE配置币种已存在"),
    /*oreRecord*/
    PLEASE_SELECT_FILTER_COIN(70039, "请选择筛选币种"),

    PUBLIC_NON(80001, "公募不存在"),
    EXCHANGE_NON(80002, "兑换不存在"),
    /*browser*/
    INVALID_ACCOUNT(80003, "We find nothing about it"),
    /*market*/
    MARKET_NON(90001,"市场不存在"),
    /*admin role*/
    ROLE_NAME_EXIST(80004, "角色名称已存在"),
    /*market*/
    MARKET_ADD_FAIL(80005, " 新增币对失败"),
    MARKET_UPDATE_FAIL(80006, " 更新币对失败"),
    MARKET_DEL_FAIL(80007, " 删除币对失败"),
    MARKET_AREA_ERROR(80008, " 市场类型错误"),
    /*admin entrust*/
    ENTRUST_DIRECTION_ERROR(80009, "请选择订单方向"),

    DEPTH_ERROR(90001, " 深度错误"),

    EXIT_APPLY_COIN(100001, " 已存在申请币种"),
    DEFI_APPLY_NON(100002, " defi申请不存在"),
    DEFI_APPLY_END(100002, " defi申请已结束"),
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
