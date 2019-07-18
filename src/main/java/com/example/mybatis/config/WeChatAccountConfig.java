package com.example.mybatis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "wechat")
@Data
public class WeChatAccountConfig {

    /**
     * 微信公账号
     */
    private String wxAppId;

    /**
     * 微信公账号秘钥
     */
    private String wxAppSecret;

    /**
     * 地址
     */
    private String baseHttp;

    /**
     * 微信模版id
     */
    private Map<String, String> templateId;
}
