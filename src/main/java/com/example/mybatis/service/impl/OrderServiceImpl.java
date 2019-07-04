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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public void creatOrder(OrderDTO orderDTO)throws SellException {

        List<CartDTO> cartDTOList=new ArrayList<>();

        BigDecimal amount = new BigDecimal(0);
        //生成订单id
        String orderId = KeyUtil.getOrderId();

        //判断购物车 商品是否有效
        for (OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            //根据产品id查询产品详情
            Product productInfo=productService.findById(orderDetail.getProductId());
            //判断产品是否存在
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NO_EXIS);
            }
            //是否是上架状态
            if(productInfo.getProductStatus()== ProductStatusEnums.DOWM.getCode()){
                throw new SellException(ResultEnum.PRODUCT_DOWN);
            }
            OrderDetail orderDetail1=new OrderDetail();
            BeanUtils.copyProperties(productInfo,orderDetail1);
            //设置不同的orderDetailId
            orderDetail.setDetailId(KeyUtil.getOrderId());
            //设置同一个orderId
            orderDetail.setOrderId(orderId);
            //插入订单详情表
            orderDetailMapper.insertSelective(orderDetail1);

            //计算总金额
            amount=productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(amount);

            CartDTO cartDTO=new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }

        //生成订单主表插入数据
        OrderMaster orderMaster =new OrderMaster();
        orderMaster.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(amount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterMapper.insertSelective(orderMaster);

        //减去对应产品库存
        productService.decreaseStock(cartDTOList);

    }
}
