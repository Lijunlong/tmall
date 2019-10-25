package com.tmall.quartz.util;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.ReflectionUtils;

import com.tmall.util.SpringContextHolder;

/**
 * 执行定时任务
 * Created by Mr.Li on 2019/08/06
 */
public class QuartzRunnable implements Runnable {
	
	private Logger logger = LogManager.getLogger(QuartzRunnable.class);
	
	private Object target;
	private Method method;
	private String params;

	QuartzRunnable(String beanName, String methodName, String params)
			throws NoSuchMethodException, SecurityException {
		this.target = SpringContextHolder.getBean(beanName);
		this.params = params;

		if (StringUtils.isNotBlank(params)) {
			this.method = target.getClass().getDeclaredMethod(methodName, String.class);
		} else {
			this.method = target.getClass().getDeclaredMethod(methodName);
		}
	}

	@Override
	public void run() {
		try {
			ReflectionUtils.makeAccessible(method);
			if (StringUtils.isNotBlank(params)) {
				method.invoke(target, params);
			} else {
				method.invoke(target);
			}
		} catch (Exception e) {
			logger.error("定时任务执行失败",e);
		}
	}

}
