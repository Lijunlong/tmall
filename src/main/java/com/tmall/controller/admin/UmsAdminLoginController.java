package com.tmall.controller.admin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.tmall.dto.ImgResult;
import com.tmall.dto.UmsAdminUpdatePasswordParam;
import com.tmall.dto.UserLoginParam;
import com.tmall.model.UmsAdmin;
import com.tmall.service.UmsAdminLoginService;
import com.tmall.service.UmsPermissionService;
import com.tmall.util.CacheUtils;
import com.tmall.util.VerifyCodeUtils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
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
	private UmsAdminLoginService umsAdminLoginService;
	@Autowired
	private UmsPermissionService umsPermissionService;
	@Autowired
	private CacheUtils cacheUtils;
	@Value("${jwt.tokenHeader}")
	private String tokenHeader;
	@Value("${jwt.tokenHead}")
	private String tokenHead;
	
	@SuppressWarnings("static-access")
	@Log("用户登录")
	@ApiOperation(value = "登录以后返回token")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(@RequestBody UserLoginParam userLoginParam, BindingResult result) {
		String uuid = userLoginParam.getUuid();
		//查询验证码
		String code = (String) cacheUtils.get(uuid);
		//清除验证码
		cacheUtils.delete(uuid);
		if (StringUtils.isBlank(code)) {
			return CommonResult.validateFailed("验证码已过期");
		}
		if (StringUtils.isBlank(userLoginParam.getCode()) || !userLoginParam.getCode().equalsIgnoreCase(code)) {
			return CommonResult.validateFailed("验证码错误");
		}
		
		String token = umsAdminLoginService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
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
		String refreshToken = umsAdminLoginService.refreshToken(token);
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
		UmsAdmin umsAdmin = umsAdminLoginService.getUserByUsername(username);
		if (umsAdmin == null) {
			return CommonResult.failed("获取当前登录用户信息失败，当前登录信息为空");
		}else {
			String[] umsPermissionName = umsPermissionService.getUmsPermissionNameByAdminId(umsAdmin.getId());
			Map<String, Object> data = new HashMap<>();
			data.put("username", umsAdmin.getUsername());
			data.put("phone", umsAdmin.getTelphone());
			data.put("email", umsAdmin.getEmail());
			data.put("dept", umsAdmin.getDept());
			data.put("job", umsAdmin.getJob());
			data.put("createTime", umsAdmin.getCreateTime());
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
	
	@ApiOperation(value = "修改密码")
	@RequestMapping(value = "/update_pass", method = RequestMethod.POST)
	@ResponseBody
	public Object updatePass(@RequestBody UmsAdminUpdatePasswordParam umsAdminUpdatePasswordParam) {
		Map<String, String> map = umsAdminLoginService.updatePassword(umsAdminUpdatePasswordParam);
		String errMsg = map.get("error");
		if (errMsg != null && !"".equals(errMsg)) {
			return CommonResult.failed(errMsg);
		}else {
			return CommonResult.success("密码修改成功");
		}
	}
	
	@SuppressWarnings("static-access")
	@ApiOperation(value = "获取验证码")
	@RequestMapping(value = "/vCode", method = RequestMethod.GET)
	@ResponseBody
	public Object getCode() throws IOException {
		//生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        String uuid = IdUtil.simpleUUID();
        //放入缓存中
        cacheUtils.set(uuid, verifyCode, 30L);
        int w = 111, h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        try {
        	return CommonResult.success(new ImgResult(Base64.encode(stream.toByteArray()),uuid));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            stream.close();
        }
	}
	
}
