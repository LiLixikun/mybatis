package com.example.mybatis.service;

import com.example.mybatis.entity.UserInfo;

import java.util.List;

public interface UserService {
    /**
     * 查询所有用户
     *
     * @return
     */
    List<UserInfo> getUsers();

    /**
     * 根据id查询详情
     *
     * @param openId
     * @return
     */
    UserInfo getUserById(String openId);

    /**
     * 添加用户
     * @param userInfo
     * @return
     */
    void addUser(UserInfo userInfo);

    /**
     * 修改用户信息
     *
     * @param openId
     * @param userInfo
     * @return
     */
    void updateUserById(String openId, UserInfo userInfo);

    /**
     * 删除
     *
     * @param openId
     */
    void delUserById(String openId);
}
