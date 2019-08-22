package com.tmall.test;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.tmall.DemoApplicationTests;
import com.tmall.util.MailUtil;

public class MyTest extends DemoApplicationTests {
	
	Logger logger = LogManager.getLogger(MyTest.class);
	
	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private TemplateEngine templateEngine;

	@Test
	public void log() {
		logger.info("日志测试");
		logger.error("日志测试");
		logger.debug("日志测试");
	}
	
	
	@Test
	public void sendSimpleMail() {
		String to = "190690229@qq.com";
		String subject = "测试";
		String content = "发送一个简单格式的邮件，测试";
		mailUtil.sendSimpleMail(to, subject, content);
	}

	/**
	 * 发送一个HTML格式(富文本邮件)的邮件
	 */
	@Test
	public void sendHTMLMail() {
		String to = "190690229@qq.com";
		String subject = "发送一个HTML格式(富文本邮件)的邮件，测试";
		StringBuilder sb = new StringBuilder();
		sb.append("<h2>SpirngBoot测试邮件HTML</h2>").append("<p style='text-align:left'>这是一封HTML邮件...</p>")
				.append("<p> 时间为：" + new Date() + "</p>");
		mailUtil.sendHTMLMail(to, subject, sb.toString());
	}

	/**
	 * 发送带附件格式的邮件
	 */
	@Test
	public void sendAttachmentMail() {
		String to = "190690229@qq.com";
		String subject = "测试";
		String content = "发送带附件格式的邮件，测试";
		mailUtil.sendAttachmentMail(to, subject, content);
	}

	/**
	 * 发送带静态资源的邮件
	 */
	@Test
	public void sendInlineMail() {

		String to = "190690229@qq.com";
		String subject = "测试";
		String content = "发送带静态资源的邮件，测试";

		mailUtil.sendInlineMail(to, subject, content);
	}

	/**
	 * 发送Thymeleaf模版消息
	 */
	@Test
	public void sendTemplateMail() {
		// 注意：Context 类是在org.thymeleaf.context.Context包下的。
		String to = "190690229@qq.com";
		String subject = "发送Thymeleaf模版消息，测试";
		String content = null;
		Context context = new Context();
		// html中填充动态属性值
		context.setVariable("username", "码农用户");
		context.setVariable("url",
				"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3839527043,3300320047&fm=58&bpow=400&bpoh=648");
		content = templateEngine.process("email", context);
		mailUtil.sendHTMLMail(to, subject, content);
	}
}
