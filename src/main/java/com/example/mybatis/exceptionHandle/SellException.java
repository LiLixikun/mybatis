package com.example.mybatis.exceptionHandle;

import com.example.mybatis.enums.ResultEnum;

public class SellException extends RuntimeException {

    private int code;

    public SellException(int code,String msg){
        super(msg);
        this.code=code;
    }

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code=resultEnum.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
