package com.tmall.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * 获取 HttpServletRequest
 * Created by Mr.Li on 2019/08/22
 */
public class RequestHolder {
	
	/**
	 * 获取 HttpServletRequest
	 * @return
	 */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
