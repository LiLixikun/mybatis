package com.example.mybatis.service.modal;

import java.io.Serializable;

public class SymbolInfo  implements Serializable {

    //名称
    private String name;

    //商品的唯一标识符
    private String ticker;

    //商品说明
    private String description;

    //类别
    private String type;

    //商品交易时间
    private String session;

    //商品的交易所时区
    private String timezone;

    //最小波动
    private Integer minmov;

    private Integer minmov2;

    private Integer pointvalue;

    //价格精度
    private Integer pricescale;

    //是否有 分钟历史数据
    private Boolean has_intraday;

    private Boolean has_no_volume;

}
