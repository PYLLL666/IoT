package com.llp.service;

import com.llp.pojo.User1;

/**
 * description:业务逻辑接口
 * create by Administrator
 * create on 2021/06/10/11:26
 */
public interface UserService {

    /**
     * 根据账号密码查询用户
     * @param username
     * @param password
     * @return
     */
    User1 findUser(String username, String password);
}
