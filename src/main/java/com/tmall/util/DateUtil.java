package com.tmall.util;

import java.sql.Timestamp;

/**
 * 时间工具类
 *
 */
public class DateUtil {
	
	public DateUtil() {
		super();
	}

	/**
	 * 获取当前时间
	 * @return Timestamp
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
}
