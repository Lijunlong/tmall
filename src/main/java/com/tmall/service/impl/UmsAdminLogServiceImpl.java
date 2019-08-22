package com.tmall.service.impl;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.dao.UmsAdminLogMapper;
import com.tmall.model.UmsAdminLog;
import com.tmall.service.UmsAdminLogService;

@Service
public class UmsAdminLogServiceImpl implements UmsAdminLogService {

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
	}

}
