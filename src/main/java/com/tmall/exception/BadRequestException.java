package com.tmall.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import org.springframework.http.HttpStatus;
import lombok.Getter;

/**
 * @author Zheng Jie
 * @date 2018-11-23 统一异常处理
 */
@Getter
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Integer status = BAD_REQUEST.value();

	public BadRequestException(String msg) {
		super(msg);
	}

	public BadRequestException(HttpStatus status, String msg) {
		super(msg);
		this.status = status.value();
	}
}
