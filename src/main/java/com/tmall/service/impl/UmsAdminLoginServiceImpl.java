package com.tmall.service.impl;

import java.util.List;

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
import com.tmall.dto.UserLoginParam;
import com.tmall.model.UmsAdmin;
import com.tmall.service.UmsAdminLoginService;
import com.tmall.util.Constant;
import com.tmall.util.DateUtil;
import com.tmall.util.JwtTokenUtil;

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

	@Override
	public UmsAdmin getUserByUsername(String username) {
		List<UmsAdmin> userList = umsAdminLoginMapper.selectUserByUsername(username);
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
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

}
