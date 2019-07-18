package com.example.mybatis.entity;

import lombok.Data;

import java.util.List;

@Data
public class TradingView {

    private List<String> c;

    private List<String> h;

    private List<String> l;

    private String s;

    private List<String> o;

    private List<String> v;

    private List<String> t;
}
