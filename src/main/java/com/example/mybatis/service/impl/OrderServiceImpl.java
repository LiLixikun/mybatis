package com.example.mybatis.service.impl;

import com.example.mybatis.DTO.CartDTO;
import com.example.mybatis.DTO.OrderDTO;
import com.example.mybatis.entity.OrderDetail;
import com.example.mybatis.entity.OrderMaster;
import com.example.mybatis.entity.Product;
import com.example.mybatis.enums.OrderStatusEnum;
import com.example.mybatis.enums.PayStatusEnum;
import com.example.mybatis.enums.ProductStatusEnums;
import com.example.mybatis.enums.ResultEnum;
import com.example.mybatis.exceptionHandle.SellException;
import com.example.mybatis.mapper.OrderDetailMapper;
import com.example.mybatis.mapper.OrderMasterMapper;
import com.example.mybatis.service.OrderService;
import com.example.mybatis.service.ProductService;
import com.example.mybatis.utils.KeyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public void creatOrder(OrderDTO orderDTO) throws SellException {

        List<CartDTO> cartDTOList = new ArrayList<>();

        BigDecimal amount = new BigDecimal(0);
        //生成订单id
        String orderId = KeyUtil.getOrderId();

        //判断购物车 商品是否有效
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            //根据产品id查询产品详情
            Product productInfo = productService.findById(orderDetail.getProductId());
            //判断产品是否存在
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NO_EXIS);
            }
            //是否是上架状态
            if (productInfo.getProductStatus() == ProductStatusEnums.DOWM.getCode()) {
                throw new SellException(ResultEnum.PRODUCT_DOWN);
            }
            OrderDetail orderDetail1 = new OrderDetail();
            BeanUtils.copyProperties(productInfo, orderDetail1);
            //设置本次消费商品数量
            orderDetail1.setProductQuantity(orderDetail.getProductQuantity());
            //设置不同的orderDetailId
            orderDetail1.setDetailId(KeyUtil.getOrderId());
            //设置同一个orderId
            orderDetail1.setOrderId(orderId);
            //插入订单详情表
            orderDetailMapper.insertSelective(orderDetail1);

            //计算总金额
            amount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(amount);

            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }

        //生成订单主表插入数据
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(amount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterMapper.insertSelective(orderMaster);

        //减去对应产品库存
        productService.decreaseStock(cartDTOList);

    }

    @Override
    public PageInfo<OrderMaster> findAllOrder(int pageNum, int pageSize) {
        List<OrderMaster> masterList = orderMasterMapper.selectAll();
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<OrderMaster> pageInfo = new PageInfo<>(masterList, pageSize);
        return pageInfo;
    }

    @Override
    public PageInfo<OrderMaster> findOrderByUser(String openId, int pageNum, int pageSize) {
        List<OrderMaster> masterList = orderMasterMapper.findOrderByUser(openId);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<OrderMaster> pageInfo = new PageInfo<>(masterList, pageSize);
        return pageInfo;
    }

    @Override
    public OrderDTO findOrderById(String orderId) throws SellException {
        //查询订单主表
        OrderMaster master = orderMasterMapper.selectByPrimaryKey(orderId);
        if (master == null) {
            throw new SellException(ResultEnum.ORDER_NO_EXIT);
        }
        //查询订单详情表 同一个订单可以有多个商品详情
        List<OrderDetail> orderDetailList = orderDetailMapper.findOrderLists(orderId);
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(master, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    @Transactional
    public void cancelOrder(String orderId) {
        //查询订单主表
        OrderMaster master = orderMasterMapper.selectByPrimaryKey(orderId);
        if (master == null) {
            throw new SellException(ResultEnum.ORDER_NO_EXIT);
        }

        //判断当前可否取消
        if (!master.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new SellException(ResultEnum.ORDER_NOT_CANCEL);
        }

        //取消订单
        master.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        int result = orderMasterMapper.updateByPrimaryKeySelective(master);
        if (result != 1) {
            throw new SellException(ResultEnum.ORDER_CANCEL_ERR);
        }

        //订单取消减库存
        OrderDTO orderDTO = findOrderById(orderId);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            throw new SellException(ResultEnum.CART_NOT_NULL);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(orderDetail -> new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity())).collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
    }

    @Override
    public void finishOrder(String orderId) {
        //查询订单主表
        OrderMaster master = orderMasterMapper.selectByPrimaryKey(orderId);
        if (master == null) {
            throw new SellException(ResultEnum.ORDER_NO_EXIT);
        }
        if(master.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            throw new SellException(ResultEnum.ORDER_HAS_FINISH);
        }
        //完成订单
        master.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
       int result= orderMasterMapper.updateByPrimaryKeySelective(master);
       if(result==0){
           throw new SellException(ResultEnum.ORDER_NOT_FINISH);
       }
    }

    @Override
    public void payOrder(String orderId) {
        //查询订单主表
        OrderMaster master = orderMasterMapper.selectByPrimaryKey(orderId);
        if (master == null) {
            throw new SellException(ResultEnum.ORDER_NO_EXIT);
        }

        //修改支付状态
        master.setOrderStatus(PayStatusEnum.SUCCESS.getCode());
        int result= orderMasterMapper.updateByPrimaryKeySelective(master);
        if(result==0){
            throw new SellException(ResultEnum.ORDER_NOT_FINISH);
        }
    }
}
