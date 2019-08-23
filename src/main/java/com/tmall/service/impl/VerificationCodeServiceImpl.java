package com.tmall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.dao.VerificationCodeMapper;
import com.tmall.model.VerificationCode;
import com.tmall.service.VerificationCodeService;
import com.tmall.util.Constant;

import cn.hutool.core.util.RandomUtil;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
	
	@Autowired
	private VerificationCodeMapper verificationCodeMapper;
	
	@Override
	public int resetEmail(VerificationCode verificationCodeParam) {
		String type = verificationCodeParam.getType();//类型
		String value = verificationCodeParam.getValue();//值
		String scenes = verificationCodeParam.getScenes();//业务名称
		VerificationCode verificationCode = verificationCodeMapper.selectValidVerificationCodeByTypeAndValueAndScenes(type,value,scenes);
		if (verificationCode == null) {
			//数据库没有有效的验证码
			//生成新的验证码，保存到数据库，并发送邮件通知
			String code = RandomUtil.randomStringUpper(6);
			verificationCodeParam.setCode(code);
			verificationCodeParam.setStatus(1);//状态：1有效、0过期
			verificationCodeParam.setCreateTime(new Date());
			verificationCodeMapper.insertVerificationCode(verificationCodeParam);
			//定时处理，过期将该验证码置为过期状态
			
		}else {
			//数据库有有效的验证码
			//直接发送该验证码
			
		}
		
		
		//如果没有，生成验证码
		//保存到验证码库
		//定时改变验证码的状态
		//发送邮箱通知
		
		
		
		return 0;
	}

}
