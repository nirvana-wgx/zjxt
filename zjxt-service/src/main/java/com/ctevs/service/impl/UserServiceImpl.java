package com.ctevs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctevs.base.UserDao;
import com.ctevs.po.UserBean;
import com.ctevs.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public UserBean getUser(String loginName, String password) {

        return userDao.getUser(loginName, password);
    }

    @Override
    public UserBean getUserInfo(String userId) {

        return userDao.getUserInfo(userId);
    }

    @Override
    public UserBean getAccount(String account) {
        return userDao.getAccount(account);
    }

}
