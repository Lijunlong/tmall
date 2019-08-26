package com.tmall.service.impl;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmall.dao.VerificationCodeMapper;
import com.tmall.model.VerificationCode;
import com.tmall.service.VerificationCodeService;
import com.tmall.util.MailUtil;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

	protected Logger logger = LogManager.getLogger(VerificationCodeServiceImpl.class);

	@Autowired
	private VerificationCodeMapper verificationCodeMapper;
	@Autowired
	private MailUtil mailUtil;

	@Override
	@Transactional
	public int resetEmail(VerificationCode verificationCodeParam) {
		String code = null;//邮箱验证码
		String type = verificationCodeParam.getType();// 类型
		String value = verificationCodeParam.getValue();// 值
		String scenes = verificationCodeParam.getScenes();// 业务名称
		VerificationCode verificationCode = verificationCodeMapper.selectValidVerificationCodeByTypeAndValueAndScenes(type, value, scenes);
		if (verificationCode == null) {
			// 数据库没有有效的验证码
			// 生成新的验证码，保存到数据库，并发送邮件通知
			code = RandomUtil.randomStringUpper(6);
			verificationCodeParam.setCode(code);
			verificationCodeParam.setStatus(1);// 状态：1有效、0过期
			verificationCodeParam.setCreateTime(new Date());
			verificationCodeMapper.insertVerificationCode(verificationCodeParam);
			// 异步延迟处理，过期将该验证码置为过期状态
			this.timedDestruction(verificationCodeParam);
		} else {
			// 数据库有有效的验证码
			code = verificationCode.getCode();
		}
		//发送邮箱验证码
		String to = value;//收件人
		String subject = "tmall后台管理系统";//主题
		String content = "尊敬的用户，您好：\r\n您正在申请邮箱验证，您的验证码为：" + code;
		//发送邮件
		mailUtil.sendSimpleMail(to, subject, content);
		return 1;
	}

	/**
	 * 异步线程，延迟处理，作废验证码
	 * 
	 * @param verificationCodeParam
	 */
	private void timedDestruction(VerificationCode verificationCodeParam) {
		try {
			ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
			scheduledExecutorService.schedule(new TimerTask() {
				@Override
				public void run() {
					// 延迟60s执行，作废验证码
					verificationCodeParam.setStatus(0);// 状态：1有效、0过期
					verificationCodeMapper.updateVerificationCode(verificationCodeParam);
				}
			}, 60, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("[作废验证码异常]", e);
			e.printStackTrace();
		}
	}

	@Override
	public VerificationCode selectValidVerificationCodeByTypeAndValueAndScenes(String type, String value, String scenes) {
		return verificationCodeMapper.selectValidVerificationCodeByTypeAndValueAndScenes(type, value, scenes);
	}

}
