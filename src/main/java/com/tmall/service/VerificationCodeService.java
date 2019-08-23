package com.tmall.service;

import com.tmall.model.VerificationCode;

public interface VerificationCodeService {
	
	/**
	 * 重置邮箱
	 * @param verificationCode 前端验证码参数集
	 * @return
	 */
	int resetEmail(VerificationCode verificationCode);

}
