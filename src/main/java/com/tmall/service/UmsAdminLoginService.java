package com.tmall.service;

import com.tmall.dto.UserLoginParam;
import com.tmall.model.UmsAdmin;

public interface UmsAdminLoginService {
	
	/**
	 * 通过用户名获取用户
	 * @param username 用户名
	 * @return User
	 */
	UmsAdmin getUserByUsername(String username);
	
	/**
	 * 用户注册
	 * @param userLoginParam 用户注册参数
	 * @return User
	 */
	UmsAdmin register(UserLoginParam userLoginParam);
	
	/**
	 * 用户登录后返回token
	 * @param username 用户名
	 * @param password 密码
	 * @return token
	 */
	String login(String username, String password);
	
	/**
	 * 刷新token
	 * @param oldToken 旧token
	 * @return 新token
	 */
	String refreshToken(String oldToken);
	
}
