package com.example.mybatis.service.impl;

import com.example.mybatis.DTO.OrderDTO;
import com.example.mybatis.entity.OrderMaster;
import com.example.mybatis.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class PushMessageServiceImplTest {

    static final String orderId="O1562745015236";

    @Autowired
    private PushMessageServiceImpl pushMessageService;

    @Autowired
    private OrderService orderService;
    @Test
    public void orderStatus() {
        OrderDTO orderDTO=orderService.findOrderById(orderId);

    }
}