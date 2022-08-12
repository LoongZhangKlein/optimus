package com.optimus.enums;

/**
 * 全局枚举类
 * @author DragonZhang
 */
public enum GlobalEnum {
    // 公用

    MSG_BLANK("000","返回为空"),
    MSG_NOT_FULL("001","信息不全"),
    NOT_LOG_IN("002","未登录"),
    USER_EXISTS("003","用户已存在"),
    SUCCESS("200","请求成功"),
    LOGIN_ERROR("401","登录失败"),
    TOKEN_FAILURE("402","token无效"),
    TOKEN_MISS("403","token不存在"),
    AUTH_DENIED("404","权限不足"),
    ERROR("500","请求失败"),
    ;

    private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    GlobalEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
