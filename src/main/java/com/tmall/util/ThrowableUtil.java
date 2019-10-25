package com.tmall.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 	异常工具
 * Created by Mr.Li on 2019/08/06
 */
public class ThrowableUtil {
	
	/**
	 * 	获取堆栈信息
	 * @param throwable
	 * @return
	 */
	public static String getStackTrace(Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			throwable.printStackTrace(pw);
			return sw.toString();
		} finally {
			pw.close();
		}
	}
}
