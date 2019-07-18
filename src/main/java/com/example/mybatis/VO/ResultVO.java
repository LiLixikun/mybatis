package com.example.mybatis.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO <T> implements Serializable {

    private static final long serialVersionUID = 4369589217416363090L;
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
