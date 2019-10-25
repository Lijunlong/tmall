package com.tmall.service;

import java.util.Map;

import com.tmall.dto.UmsAdminUpdatePasswordParam;
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
	
	/**
	 * 修改密码
	 * @param umsAdminUpdatePasswordParam 前端密码参数
	 * @return
	 */
	Map<String, String> updatePassword(UmsAdminUpdatePasswordParam umsAdminUpdatePasswordParam);
	
}
