package com.tmall.controller.admin;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall.aop.log.Log;
import com.tmall.common.api.CommonResult;
import com.tmall.dto.UserLoginParam;
import com.tmall.model.UmsAdmin;
import com.tmall.service.UmsAdminLoginService;
import com.tmall.service.UmsPermissionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 后台管理登录控制 Created by Mr.Li on 2019/07/23
 */
@Controller
@Api(tags = "UmsAdminLoginController", description = "后台用户登录")
@RequestMapping("/admin")
public class UmsAdminLoginController {
	@Autowired
	private UmsAdminLoginService userService;
	@Autowired
	private UmsPermissionService umsPermissionService;
	@Value("${jwt.tokenHeader}")
	private String tokenHeader;
	@Value("${jwt.tokenHead}")
	private String tokenHead;
	
	@Log("用户登录")
	@ApiOperation(value = "登录以后返回token")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(@RequestBody UserLoginParam userLoginParam, BindingResult result) {
		String token = userService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
		if (token == null) {
			return CommonResult.validateFailed("用户名或密码错误");
		}
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);
		tokenMap.put("tokenHead", tokenHead);
		return CommonResult.success(tokenMap);
	}

	@ApiOperation(value = "刷新token")
	@RequestMapping(value = "/token/refresh", method = RequestMethod.GET)
	@ResponseBody
	public Object refreshToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String refreshToken = userService.refreshToken(token);
		if (refreshToken == null) {
			return CommonResult.failed();
		}
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", refreshToken);
		tokenMap.put("tokenHead", tokenHead);
		return CommonResult.success(tokenMap);
	}

	@ApiOperation(value = "获取当前登录用户信息")
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@ResponseBody
	public Object getAdminInfo(Principal principal) {
		String username = principal.getName();
		UmsAdmin umsAdmin = userService.getUserByUsername(username);
		if (umsAdmin == null) {
			return CommonResult.failed("获取当前登录用户信息失败，当前登录信息为空");
		}else {
			String[] umsPermissionName = umsPermissionService.getUmsPermissionNameByAdminId(umsAdmin.getId());
			Map<String, Object> data = new HashMap<>();
			data.put("username", umsAdmin.getUsername());
			data.put("roles", umsPermissionName);
			data.put("icon", umsAdmin.getIcon());
			return CommonResult.success(data);
		}
	}
	
	@ApiOperation(value = "登出功能")
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public Object logout() {
		return CommonResult.success(null);
	}
}
