package com.example.mybatis.exceptionHandle;

import com.example.mybatis.VO.ResultVO;
import com.example.mybatis.enums.ResultEnum;
import com.example.mybatis.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handle(Exception e) {
        if (e instanceof SellException) {
            SellException myException = (SellException) e;
            return ResultUtil.err(myException.getCode(), myException.getMessage());
        }
        return ResultUtil.err(ResultEnum.sell_err.getCode(),ResultEnum.sell_err.getMsg());
    }
}
