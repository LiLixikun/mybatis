package com.example.mybatis.utils;

import com.example.mybatis.VO.ResultVO;

public class ResultUtil {

    public static ResultVO success(Object o){
        ResultVO resultVO=new ResultVO(200,"成功",o);
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO err(Integer code,String msg){
        ResultVO resultVO=new ResultVO(code,msg,null);
        return resultVO;
    }
}
