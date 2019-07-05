package com.example.mybatis.mapper;

import com.example.mybatis.entity.OrderDetail;

import java.util.List;

public interface OrderDetailMapper {

    List<OrderDetail> findOrderLists(String orderId);

    int deleteByPrimaryKey(String detailId);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(String detailId);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
}