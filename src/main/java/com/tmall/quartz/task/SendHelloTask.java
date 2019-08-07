package com.tmall.quartz.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.tmall.util.MailUtil;

/**
 * 发送hello邮件 Created by Mr.Li on 2019/08/07
 */
@Component
public class SendHelloTask {
	
	private Logger logger = LogManager.getLogger(SendHelloTask.class);

	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private TemplateEngine templateEngine;
	
	public void run() {
		String targetTo = "190690229@qq.com";
		sendEmail(targetTo);
		logger.info("邮件发送成功");
	}

	private void sendEmail(String targetTo) {
		// 注意：Context 类是在org.thymeleaf.context.Context包下的。
		String subject = "★★★七夕特别礼物，一头猪的爱★★★";
		String content = null;
		Context context = new Context();
		content = templateEngine.process("hello", context);
		mailUtil.sendHTMLMail(targetTo, subject, content);
	}

}
