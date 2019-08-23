package com.tmall.service.impl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.tmall.dao.UmsAdminLogMapper;
import com.tmall.model.UmsAdminLog;
import com.tmall.service.UmsAdminLogService;
import com.tmall.util.CommonUtil;
import com.tmall.util.SecurityUtils;

@Service
public class UmsAdminLogServiceImpl implements UmsAdminLogService {
	
	private Logger logger = LogManager.getLogger(UmsAdminLogServiceImpl.class);
	
	@Autowired
	private UmsAdminLogMapper umsAdminLogMapper;

	@Override
	public void save(String username, String ip, ProceedingJoinPoint joinPoint, UmsAdminLog log) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		com.tmall.aop.log.Log aopLog = method.getAnnotation(com.tmall.aop.log.Log.class);
		// 描述
		if (log != null) {
			log.setDescription(aopLog.value());
		}

		// 方法路径
		String methodName = joinPoint.getTarget().getClass().getName() + "." + signature.getName() + "()";

		String params = "{";
		// 参数值
		Object[] argValues = joinPoint.getArgs();
		// 参数名称
		String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
		if (argValues != null) {
			for (int i = 0; i < argValues.length; i++) {
				params += " " + argNames[i] + ": " + argValues[i];
			}
		}

		// 获取IP地址
		log.setRequestIp(ip);

		log.setMethod(methodName);
		log.setParams(params + " }");
		log.setCreater(username);
		log.setCreateTime(new Date());
		umsAdminLogMapper.insertUmsAdminLog(log);
		Long logId = log.getId();
		//异步线程，通过ip地址更新ip地址详细信息
		try {
			ExecutorService exec = Executors.newSingleThreadExecutor();
			Callable<Object> call = new UmsAdminLogUpdateAddress(logId, ip);
			exec.submit(call);
		} catch (Exception e) {
			logger.error("[异步线程执行异常，通过ip地址获取ip地址详情失败]", e);
		}
	}

	@Override
	public List<UmsAdminLog> getLogs(Integer pageSize, Integer pageNum) {
		UmsAdminLog umsAdminLog = new UmsAdminLog();
		umsAdminLog.setLogType("INFO");
		//获取日志列表
		PageHelper.startPage(pageNum, pageSize);
		List<UmsAdminLog> umsAdminLogList = umsAdminLogMapper.selectUmsAdminLogList(umsAdminLog);
		return umsAdminLogList;
	}

	@Override
	public List<UmsAdminLog> getErrorLogs(Integer pageSize, Integer pageNum) {
		UmsAdminLog umsAdminLog = new UmsAdminLog();
		umsAdminLog.setLogType("ERROR");
		//获取日志列表
		PageHelper.startPage(pageNum, pageSize);
		List<UmsAdminLog> umsAdminLogList = umsAdminLogMapper.selectUmsAdminLogList(umsAdminLog);
		return umsAdminLogList;
	}
	
	class UmsAdminLogUpdateAddress implements Callable<Object>{
		private Long logId;
		private String ip;
		public UmsAdminLogUpdateAddress(Long logIp, String ip) {
			super();
			this.logId = logIp;
			this.ip = ip;
		}
		@Override
		public Object call() throws Exception {
			String address = CommonUtil.getAddressByIp(ip);
			UmsAdminLog umsAdminLog = new UmsAdminLog();
			umsAdminLog.setAddress(address);
			umsAdminLog.setId(logId);
			umsAdminLogMapper.updateUmsAdminLogAddressById(umsAdminLog);
			return null;
		}
		
	}

	@Override
	public List<UmsAdminLog> getCurrentUserLogs(Integer pageSize, Integer pageNum) {
		//当前登录用户
		String username = SecurityUtils.getUsername();
		UmsAdminLog umsAdminLog = new UmsAdminLog();
		umsAdminLog.setCreater(username);
		//获取当前登录用户操作日志
		PageHelper.startPage(pageNum, pageSize);
		List<UmsAdminLog> logList = umsAdminLogMapper.selectUmsAdminLogList(umsAdminLog);
		return logList;
	}
}
