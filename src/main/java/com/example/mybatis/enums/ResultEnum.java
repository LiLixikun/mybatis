package com.example.mybatis.enums;

//全局返回信息枚举
public enum ResultEnum {

    //-10000 开头用户错误信息
    USER_NO_EXIX(-10000,"用户不存在"),

    //-20000 开头商品错误
    PRODUCT_NO_EXIS(-20000,"商品不存在"),

    PRODUCT_DOWN(-20001,"商品已经下架"),

    CART_NOT_NULL(-20002,"购物车不能为空"),

    PRODUCT_STOCK_ERR(-20003,"库存不足"),

    //-30000 订单错误
    ORDER_NO_EXIT(-30000,"订单不存在"),

    ORDER_NOT_CANCEL(-30001,"当前订单无法取消"),

    ORDER_CANCEL_ERR(-30002,"取消订单失败"),

    ORDER_NOT_FINISH(-30003,"当前订单无法完结"),

    ORDER_HAS_FINISH(-30004,"当前订单已经完结"),

    //参数校验不通过 -200000
    FORM_ERR(-200000,"参数不正确"),

    //未知错误 -100000
    SELL_ERR(-100000,"系统错误"),
    ;
    private int code;

    private String msg;

    private ResultEnum(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

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
}
