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
	
	/**
	 * 获取操作日志
	 * @param creater 操作人
	 * @param ip IP地址
	 * @param description 描述
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	List<UmsAdminLog> getLogs(String creater, String ip, String description, Integer pageSize, Integer pageNum);
	
	/**
	 * 获取异常日志
	 * @param creater 操作人
	 * @param ip IP地址
	 * @param description 描述
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	List<UmsAdminLog> getErrorLogs(String creater, String ip, String description, Integer pageSize, Integer pageNum);
	
	/**
	 * 查询当前用户操作日志
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	List<UmsAdminLog> getCurrentUserLogs(Integer pageSize, Integer pageNum);
	
}
