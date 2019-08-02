package com.tmall.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.tmall.bo.AdminUserDetails;
import com.tmall.component.JwtAuthenticationTokenFilter;
import com.tmall.component.RestAuthenticationEntryPoint;
import com.tmall.component.RestfulAccessDeniedHandler;
import com.tmall.model.UmsAdmin;
import com.tmall.model.UmsPermission;
import com.tmall.service.UmsPermissionService;
import com.tmall.service.UmsAdminLoginService;

/**
 * SpringSecurity的配置 Created by macro on 2018/4/26.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	@Autowired
	private UmsAdminLoginService userService;
	
	@Autowired
	private UmsPermissionService permissionService;
	
	@Autowired
	private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf()// 由于使用的是JWT，我们这里不需要csrf
				.disable().sessionManagement()// 基于token，所以不需要session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
						"/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js", "/swagger-resources/**",
						"/v2/api-docs/**", "/upload/**")
				.permitAll().antMatchers("/admin/login", "/admin/register")// 对登录注册要允许匿名访问
				.permitAll().antMatchers(HttpMethod.OPTIONS)// 跨域请求会先进行一次options请求
				.permitAll().antMatchers("/**")// 测试时全部运行访问
				.permitAll().anyRequest()// 除上面外的所有请求全部需要鉴权认证
				.authenticated();
		// 禁用缓存
		httpSecurity.headers().cacheControl();
		// 添加JWT filter
		httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		// 添加自定义未授权和未登录结果返回
		httpSecurity.exceptionHandling().accessDeniedHandler(restfulAccessDeniedHandler)
				.authenticationEntryPoint(restAuthenticationEntryPoint);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(encoder());
	}
	
	@Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public UserDetailsService userDetailsService() {
		// 获取登录用户信息
		return username -> {
			UmsAdmin user = userService.getUserByUsername(username);
			if (user != null) {
				List<UmsPermission> permissionList;
				try {
					permissionList = permissionService.getUmsPermissionList(user.getId());
					return new AdminUserDetails(user, permissionList);
				} catch (Exception e) {
					logger.error("[异常]{}", e);
				}
			}
			throw new UsernameNotFoundException("用户名或密码错误");
		};
	}

	@Bean
	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
		return new JwtAuthenticationTokenFilter();
	}

	/**
	 * 允许跨域调用的过滤器
	 */
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		config.setAllowCredentials(true);
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return new CorsFilter(source);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
