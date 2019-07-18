package com.example.mybatis.service;

import com.example.mybatis.entity.OrderMaster;

public interface PushMessageService {

    /**
     * 消息推送
     * @param orderMaster
     */
    void orderStatus(OrderMaster orderMaster);
}
