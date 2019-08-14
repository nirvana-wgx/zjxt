package com.ctevs.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctevs.api.exception.AppException;
import com.ctevs.po.UserBean;
import com.ctevs.service.TokenService;
import com.ctevs.service.UserService;
 
 

@RestController
@RequestMapping("/zjxt-api/user")
public class UserController  extends ControllerSupport{
	
	@Autowired
	private UserService userService;
	 @Autowired
	private TokenService tokenService;
	
	 @RequestMapping("/login")
	 public Map<String, Object>Login(String loginName, String password) throws AppException,IOException {
		 if(StringUtils.isEmpty(loginName)) {
			 throw new AppException("请输入用户名");
		 }
		 
		 if(StringUtils.isEmpty(password)) {
			 throw new AppException("请输入密码");
		 }
		 
		 Map<String, Object> map = new HashMap<>();
		 UserBean userBean = userService.getUser(loginName, password);
		 if(userBean == null) {
			 throw new AppException("用户账号错误");
		 }
		 
		 String token = tokenService.getToken(userBean.id);
		 map.put("userInfo", userBean);
		 map.put("myToken", token);
		 success(map);
		 return map;
	 }
	 
	 @RequestMapping("/getUserInfo")
	 public Map<String, Object>getUserInfo(String userId) throws AppException,IOException {
		 if(StringUtils.isEmpty(userId)) {
			 throw new AppException("请输入用户id");
		 }
		  
		 Map<String, Object> map = new HashMap<>();
		 UserBean userBean = userService.getUserInfo(userId);
		 if(userBean == null) {
			 throw new AppException("用户id错误");
		 }
		 map.put("userInfo", userBean);
		 success(map);
		 return map;
	 }
	 
	 @RequestMapping("/getAccount")
	 public Map<String, Object>getAccount(String account) throws AppException,IOException {
		 if(StringUtils.isEmpty(account)) {
			 throw new AppException("请输入用户account");
		 }
		  
		 Map<String, Object> map = new HashMap<>();
		 UserBean userBean = userService.getAccount(account);
		 if(userBean == null) {
			 throw new AppException("用户account错误");
		 }
		 map.put("userInfo", userBean);
		 success(map);
		 return map;
	 }
}
