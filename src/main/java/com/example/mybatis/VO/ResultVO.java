package com.example.mybatis.VO;

import lombok.Data;

@Data
public class ResultVO <T>{
    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回实体
     */
    private T data;


    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
