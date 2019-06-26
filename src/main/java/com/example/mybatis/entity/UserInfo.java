package com.example.mybatis.entity;

import lombok.Data;

@Data
public class UserInfo {

    /**
     * 微信唯一id
     */
    private String openId;

    /**
     * 微信昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String sex;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 头像
     */
    private String headImgUrl;

    /**
     * 微信手机号
     */
    private String phone;
}
