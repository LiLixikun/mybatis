package com.example.mybatis.DTO;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

//购物车实体
@Data
public class CartDTO {

    //商品ID
    private String productId;

    //购买数量
    @NotEmpty(message = "手机号必填")
    @Min(value = 1,message = "最少购买量一件")
    protected int productStock;

    public CartDTO(String productId,int productStock){
        this.productId=productId;
        this.productStock=productStock;
    }
}
