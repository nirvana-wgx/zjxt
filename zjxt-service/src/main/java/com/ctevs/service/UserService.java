package com.ctevs.service;

import com.ctevs.po.UserBean;

public interface UserService {

    UserBean getUser(String loginName, String password);

    UserBean getUserInfo(String userId);

    UserBean getAccount(String account);
}
