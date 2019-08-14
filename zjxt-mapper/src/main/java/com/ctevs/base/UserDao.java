package com.ctevs.base;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ctevs.po.UserBean;

@Repository
public interface UserDao {

	UserBean getUser(@Param("code") String loginName, @Param("password") String password);
	
	UserBean getUserInfo(@Param("userId") String userId);
	
	UserBean getAccount(@Param("account") String account);
}
 