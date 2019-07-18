package com.example.mybatis.service.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductModel implements Serializable {

    private static final long serialVersionUID = -3843846184914543076L;

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
