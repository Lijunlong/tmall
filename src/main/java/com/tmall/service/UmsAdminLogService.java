package com.tmall.service;

import java.util.List;

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

	List<UmsAdminLog> getLogs(Integer pageSize, Integer pageNum);

	List<UmsAdminLog> getErrorLogs(Integer pageSize, Integer pageNum);
	
	/**
	 * 查询当前用户操作日志
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	List<UmsAdminLog> getCurrentUserLogs(Integer pageSize, Integer pageNum);
	
}
