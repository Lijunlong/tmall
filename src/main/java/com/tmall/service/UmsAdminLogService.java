package com.tmall.service;

import org.aspectj.lang.ProceedingJoinPoint;

import com.tmall.model.UmsAdminLog;

public interface UmsAdminLogService {
	
	/**
	 * 添加日志信息
	 * @param username 创建人
	 * @param ip IP地址
	 * @param joinPoint ProceedingJoinPoint
	 * @param log 日志信息
	 */
	void save(String username, String ip, ProceedingJoinPoint joinPoint, UmsAdminLog log);
	
}
