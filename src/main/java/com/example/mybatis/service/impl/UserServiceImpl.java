package com.example.mybatis.service.impl;

import com.example.mybatis.entity.UserInfo;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public List<UserInfo> getUsers() {
        return mapper.getUsers();
    }

    @Override
    public UserInfo getUserById(String openId) {
        UserInfo userInfo=mapper.getUserById(openId);
        return userInfo;
    }

    @Override
    public UserInfo addUser(UserInfo userInfo) {
        return mapper.addUser(userInfo);
    }

    @Override
    public UserInfo updateUserById(String openId, UserInfo userInfo) {
        return mapper.updateUserById(openId, userInfo);
    }

    @Override
    public void delUserById(String openId) {
        mapper.delUserById(openId);
    }
}
