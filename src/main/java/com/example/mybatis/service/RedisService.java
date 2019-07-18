package com.example.mybatis.service;

public interface RedisService {

    /**
     * 儲存
     * @param key
     * @param value
     */
    void set(final String key,final String value);

    /**
     * 獲取緩存
     * @param key
     * @return
     */
    String get(final String key);
}
