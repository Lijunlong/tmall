package com.tmall.service;

import org.apache.ibatis.annotations.Param;

import com.tmall.model.VerificationCode;

public interface VerificationCodeService {
	
	/**
	 * 重置邮箱
	 * @param verificationCode 前端验证码参数集
	 * @return
	 */
	int resetEmail(VerificationCode verificationCode);
	
	/**
	 * 通过类型、值、业务名称，获取有效的验证码
	 * @param type 类型
	 * @param value 值
	 * @param scenes 业务名称
	 * @return
	 */
	VerificationCode selectValidVerificationCodeByTypeAndValueAndScenes(String type, String value, String scenes);
}
