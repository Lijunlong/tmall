package com.tmall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.tmall.exception.BadRequestException;
import com.tmall.model.UmsAdmin;
import com.tmall.service.UmsAdminLoginService;
import com.tmall.util.JwtTokenUtil;

/**
 * 基控制器
 */
public class BaseController {
	protected Logger logger = LogManager.getLogger(BaseController.class);
	
	@Value("${jwt.tokenHeader}")
	private String tokenHeader;
	@Value("${jwt.tokenHead}")
	private String tokenHead;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UmsAdminLoginService umsAdminLoginService;

	public UmsAdmin getUmsAdmin(HttpServletRequest request) {
		UmsAdmin umsAdmin = null;
		String authHeader = request.getHeader(this.tokenHeader);
		if (authHeader == null || "".equals(authHeader)) {
			throw new BadRequestException("登录Token为空");
		}else {
			try {
				String authToken = authHeader.substring(this.tokenHead.length());
				String username = jwtTokenUtil.getUserNameFromToken(authToken);
				umsAdmin = umsAdminLoginService.getUserByUsername(username);
			} catch (Exception e) {
				throw new BadRequestException("登录异常",e);
			}
		}
		return umsAdmin;
	}
}
