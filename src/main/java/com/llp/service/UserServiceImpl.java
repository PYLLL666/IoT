package com.llp.service;

import com.llp.dao.UserMapper;
import com.llp.pojo.User1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:业务逻辑实现层
 * create by Administrator
 * create on 2021/06/10/11:30
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据账号密码查找用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public User1 findUser(String username, String password) {
        return userMapper.findUser(username,password);
    }
}
