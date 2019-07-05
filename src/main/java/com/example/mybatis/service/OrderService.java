package com.example.mybatis.service;

import com.example.mybatis.DTO.OrderDTO;
import com.example.mybatis.entity.OrderMaster;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     */
    void creatOrder(OrderDTO orderDTO);

    /**
     * 后台查询所有订单
     * @return
     */
    PageInfo<OrderMaster> findAllOrder(int pageNum,int pageSize);

    /**
     * 查询个人订单信息
     * @param openId
     * @return
     */
    PageInfo<OrderMaster> findOrderByUser(String openId,int pageNum,int pageSize);


    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    OrderDTO findOrderById(String orderId);

    /**
     * 取消订单
     * @param orderId
     */
    void cancelOrder(String orderId);

    /**
     * 完结订单
     * @param orderId
     */
    void finishOrder(String orderId);

    /**
     * 订单支付
     * @param orderId
     */
    void payOrder(String orderId);
}
