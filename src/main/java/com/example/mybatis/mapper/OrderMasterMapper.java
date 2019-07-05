package com.example.mybatis.mapper;

import com.example.mybatis.entity.OrderMaster;

import java.util.List;

public interface OrderMasterMapper {

    List<OrderMaster> selectAll();

    List<OrderMaster> findOrderByUser(String openId);

    int deleteByPrimaryKey(String orderId);

    int insert(OrderMaster record);

    int insertSelective(OrderMaster record);

    OrderMaster selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderMaster record);

    int updateByPrimaryKey(OrderMaster record);
}