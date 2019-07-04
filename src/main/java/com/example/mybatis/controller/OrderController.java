package com.example.mybatis.controller;

import com.example.mybatis.DTO.OrderDTO;
import com.example.mybatis.VO.ResultVO;
import com.example.mybatis.coverter.CoverOrderFormToOrderDTO;
import com.example.mybatis.enums.ResultEnum;
import com.example.mybatis.exceptionHandle.SellException;
import com.example.mybatis.form.OrderForm;
import com.example.mybatis.service.OrderService;
import com.example.mybatis.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/buyer/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/creat")
    public ResultVO creatOrder(@Valid OrderForm orderForm, BindingResult bindingResult) throws SellException{
        if(bindingResult.hasErrors()){
            throw new SellException(ResultEnum.FORM_ERR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO= CoverOrderFormToOrderDTO.cover(orderForm);
        orderService.creatOrder(orderDTO);
        return ResultUtil.success();
    }
}
