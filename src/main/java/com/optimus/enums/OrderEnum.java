package com.optimus.enums;

public enum OrderEnum {
    WAIT_PAYMENT(0,"待付款"),
    WAIT_SHIPMENT(1,"待付款"),
    WAIT_RECEIVING(2,"待收货"),
    WAIT_EVALUATE(3,"待评价");

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    OrderEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
