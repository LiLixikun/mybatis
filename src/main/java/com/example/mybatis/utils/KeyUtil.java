package com.example.mybatis.utils;

public class KeyUtil {

    //生成orderId
    public static String getOrderId() {
        String orderId = "O";
        return orderId + System.currentTimeMillis();
    }

    public static String getProductId() {
        String productId = "P";
        return productId + System.currentTimeMillis();
    }
}
