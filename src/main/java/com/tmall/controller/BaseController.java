package com.tmall.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tmall.exception.BadRequestException;
import com.tmall.model.UmsAdmin;
import com.tmall.service.UmsAdminLoginService;
import com.tmall.util.JwtTokenUtil;

/**
 * 基控制器
 */
public class BaseController {
	protected Logger logger = LogManager.getLogger(BaseController.class);
	private String tokenHeader = "Authorization";
	private String tokenHead = "Bearer";

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
