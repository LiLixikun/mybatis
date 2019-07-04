package com.example.mybatis.DTO;

import com.example.mybatis.entity.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {

    private String orderId;

    /**
     * 姓名
     */
    private String buyerName;

    /**
     * 手机号
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openId
     */
    private String buyerOpenid;

    /**
     * 订单总额
     */
    private BigDecimal orderAmount;

    /**
     * 订单支付状态
     */
    private Integer payStatus;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    //一条订单主表可以有多个商品
    private List<OrderDetail> orderDetailList;
}
