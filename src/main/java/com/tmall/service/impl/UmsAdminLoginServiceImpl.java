package com.tmall.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tmall.dao.UmsAdminLoginMapper;
import com.tmall.dto.UmsAdminUpdatePasswordParam;
import com.tmall.dto.UserLoginParam;
import com.tmall.model.UmsAdmin;
import com.tmall.model.UmsDepartment;
import com.tmall.model.UmsJob;
import com.tmall.service.UmsAdminLoginService;
import com.tmall.service.UmsDepartmentService;
import com.tmall.service.UmsJobService;
import com.tmall.util.Constant;
import com.tmall.util.DateUtil;
import com.tmall.util.JwtTokenUtil;
import com.tmall.util.SecurityUtils;

import cn.hutool.core.lang.Snowflake;

@Service
public class UmsAdminLoginServiceImpl implements UmsAdminLoginService {
	private static final Logger logger = LoggerFactory.getLogger(UmsAdminLoginServiceImpl.class);
	private static final String tokenHead="Authorization";
	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
    private UserDetailsService userDetailsService;
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
    private Snowflake snowflake;
	@Autowired
	private UmsAdminLoginMapper umsAdminLoginMapper;
	@Autowired
	private UmsJobService umsJobService;
	@Autowired
	private UmsDepartmentService umsDepartmentService;

	@Override
	public UmsAdmin getUserByUsername(String username) {
		List<UmsAdmin> userList = umsAdminLoginMapper.selectUserByUsername(username);
		if (userList != null && userList.size() > 0) {
			UmsAdmin umsAdmin = userList.get(0);
			Long umsJobId = umsAdmin.getUmsJobId();
			if (umsJobId != null && umsJobId > 0) {
				UmsJob umsJob = umsJobService.selectUmsJobById(umsJobId);
				umsAdmin.setJob(umsJob);
			}else {
				umsAdmin.setJob(null);
			}
			Long umsDeptmentId = umsAdmin.getUmsDeptmentId();
			if (umsDeptmentId != null && umsDeptmentId > 0) {
				UmsDepartment umsDepartment = umsDepartmentService.selectUmsDepartmentById(umsDeptmentId);
				umsAdmin.setDept(umsDepartment);
			}else {
				umsAdmin.setDept(null);
			}
			return umsAdmin;
		}
		return null;
	}

	@Override
	public String login(String username, String password) {
		String token = null;
        //密码需要客户端加密后传递
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            token = jwtTokenUtil.generateToken(userDetails);
            //更新登录时间
            this.updateLoginTimeByUsername(username);
        } catch (AuthenticationException e) {
            logger.warn("登录异常:{}", e.getMessage());
        }
        return token;
	}
	
	/**
	 * 通过用户名更新登录的时间
	 * @param username
	 */
	private void updateLoginTimeByUsername(String username) {
		UmsAdmin user = new UmsAdmin();
		user.setUsername(username);
		user.setLoginTime(DateUtil.getTimestamp());
		umsAdminLoginMapper.updateLoginTimeByUsername(user);
	}

	@Override
	public String refreshToken(String oldToken) {
		String token = oldToken.substring(tokenHead.length());
        if (jwtTokenUtil.canRefresh(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
	}

	@Override
	public Map<String, String> updatePassword(UmsAdminUpdatePasswordParam umsAdminUpdatePasswordParam) {
		Map<String, String> map = new HashMap<String, String>();
		//检验旧密码是否正确
		String oldPass = umsAdminUpdatePasswordParam.getOldPass();
		String username = SecurityUtils.getUsername();
		List<UmsAdmin> umsAdminList = umsAdminLoginMapper.selectUserByUsername(username);
		if (umsAdminList != null && umsAdminList.size() > 0) {
			UmsAdmin umsAdmin = umsAdminList.get(0);
			//检验密码是否正确
			String password = umsAdmin.getPassword();
			if (passwordEncoder.matches(oldPass, password)) {
				//新密码加密
				String newPass = umsAdminUpdatePasswordParam.getNewPass();
				//修改密码
				int code = umsAdminLoginMapper.updateUmsAdminPasswordById(umsAdmin.getId(), passwordEncoder.encode(newPass));
				if (code > 0) {
					map.put("error", "");
				}else {
					map.put("error", "密码修改失败");
				}
			}else {
				map.put("error", "密码错误");
			}
		}else {
			map.put("error", "修改密码失败，当前用户不存在");
		}
		return map;
	}

}
