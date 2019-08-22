package com.tmall.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tmall.model.UmsAdminLog;
import com.tmall.service.UmsAdminLogService;
import com.tmall.util.SecurityUtils;

@Aspect
@Component
public class LogAspect {
	
	@Autowired
	private UmsAdminLogService umsAdminLogService;
	
	private long currentTime = 0L;
	
	// 切点范围
	@Pointcut("execution(* com.tmall.service..*(..))")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		try {
			currentTime = System.currentTimeMillis();
			result = joinPoint.proceed();
			UmsAdminLog log = new UmsAdminLog("INFO", System.currentTimeMillis() - currentTime);
		} catch (Throwable throwable) {
			// 监听参数为true则抛出异常，为false则捕获并不抛出异常
			if (joinPoint.getArgs().length > 0 && !(Boolean) joinPoint.getArgs()[0]) {
				result = null;
			} else {
				throw throwable;
			}
		}
		return result;
	}

	@AfterThrowing("pointcut()")
	public void afterThrowing() {
		System.out.println("---------------@AfterThrowing----------------");
	}
	
	public String getUsername() {
        try {
            return SecurityUtils.getUsername();
        }catch (Exception e){
            return "";
        }
    }
}
