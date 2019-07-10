package com.example.mybatis.service.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductModel {
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    private Integer categoryType;

    @JsonProperty("price")
    private BigDecimal productPrice;

    private Integer productStock;

    @JsonProperty("icon")
    private String productIcon;
}
