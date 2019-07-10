package com.example.mybatis.coverter;

import com.example.mybatis.DTO.OrderDTO;
import com.example.mybatis.entity.OrderDetail;
import com.example.mybatis.enums.ResultEnum;
import com.example.mybatis.exceptionHandle.SellException;
import com.example.mybatis.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class CoverOrderFormToOrderDTO {

    static public OrderDTO cover(OrderForm orderForm){

        Gson gson = new Gson();

        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetails=new ArrayList<>();
        try {
            orderDetails=gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception  e){
            throw new SellException(ResultEnum.CART_NOT_NULL);
        }
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }
}
