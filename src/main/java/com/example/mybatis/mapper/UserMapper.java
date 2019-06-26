package com.example.mybatis.mapper;

import com.example.mybatis.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 查询用户列表
     *
     * @return
     */
    List<UserInfo> getUsers();

    /**
     * 根据openid查询用户
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
    UserInfo addUser(UserInfo userInfo);

    /**
     * 修改用户信息
     *
     * @param openId
     * @return
     */
    UserInfo updateUserById(String openId, UserInfo userInfo);

    /**
     * 根据id删除
     *
     * @param openId
     */
    void delUserById(String openId);
}
