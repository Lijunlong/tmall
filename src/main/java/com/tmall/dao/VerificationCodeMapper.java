package com.tmall.dao;

import org.apache.ibatis.annotations.Param;

import com.tmall.model.VerificationCode;

public interface VerificationCodeMapper {
	
	/**
	 * 通过类型、值、业务名称，获取有效的验证码
	 * @param type 类型
	 * @param value 值
	 * @param scenes 业务名称
	 * @return
	 */
	VerificationCode selectValidVerificationCodeByTypeAndValueAndScenes(@Param("type")String type, @Param("value")String value, @Param("scenes")String scenes);

	/**
	 * 添加验证码
	 * @param verificationCode
	 * @return
	 */
	Long insertVerificationCode(VerificationCode verificationCode);
	
	/**
	 * 修改验证码状态
	 * @param verificationCode
	 * @return
	 */
	int updateVerificationCode(VerificationCode verificationCode);
	

}
