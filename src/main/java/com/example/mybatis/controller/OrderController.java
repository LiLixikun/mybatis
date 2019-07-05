package com.example.mybatis.controller;

import com.example.mybatis.DTO.OrderDTO;
import com.example.mybatis.VO.ResultVO;
import com.example.mybatis.coverter.CoverOrderFormToOrderDTO;
import com.example.mybatis.enums.ResultEnum;
import com.example.mybatis.exceptionHandle.SellException;
import com.example.mybatis.form.OrderForm;
import com.example.mybatis.service.OrderService;
import com.example.mybatis.utils.ResultUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/findUserOrders")
    public ResultVO findUserOrders(@RequestParam(value = "buyerOpenid",required = false,defaultValue = "ew3euwhd7sjw9diwkq") String buyerOpenid,
                                   @RequestParam("pageNum") int pageNum,
                                   @RequestParam("pageSize") int pageSize){

        PageInfo pageInfo=orderService.findOrderByUser(buyerOpenid,pageNum,pageSize);
        return ResultUtil.success(pageInfo);
    }

    @GetMapping("/findOrderById/{orderId}")
    public ResultVO findOrderById(@PathVariable("orderId") String orderId){
        OrderDTO orderDTO=orderService.findOrderById(orderId);
        return ResultUtil.success(orderDTO);
    }

    @GetMapping("cancelOrder/{orderId}")
    public ResultVO cancelOrder(@PathVariable("orderId") String orderId){
        orderService.cancelOrder(orderId);
        return ResultUtil.success();
    }
}
