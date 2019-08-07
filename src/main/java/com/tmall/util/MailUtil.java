package com.tmall.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * 邮件工具类 Created by Mr.Li on 2019/08/07
 */
@Component
public class MailUtil {

	private Logger logger = LogManager.getLogger(MailUtil.class);

	@Value("${spring.mail.username}")
	private String MAIL_SENDER; // 邮件发送

	@Autowired
	private JavaMailSender javaMailSender;

	/**
	 * 发送一个简单格式的邮件
	 *
	 * @param mailBean
	 */
	public void sendSimpleMail(String to, String subject, String content) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			// 邮件发送人
			mailMessage.setFrom(MAIL_SENDER);
			// 邮件接收人
			mailMessage.setTo(to);
			// 邮件主题
			mailMessage.setSubject(subject);
			// 邮件内容
			mailMessage.setText(content);
			// 文本邮件抄送使用：copyTo 抄送人
			// simpleMailMessage.copyTo(copyTo);

			javaMailSender.send(mailMessage);
		} catch (Exception e) {
			logger.error("邮件发送失败", e.getMessage());
		}
	}

	/**
	 * 发送一个HTML格式(富文本邮件)的邮件
	 *
	 * @param mailBean
	 */
	public void sendHTMLMail(String to, String subject, String content) {
		MimeMessage mimeMailMessage = null;
		try {
			mimeMailMessage = javaMailSender.createMimeMessage();
			// true 表示需要创建一个multipart message
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMailMessage, true);
			messageHelper.setFrom(MAIL_SENDER);
			messageHelper.setTo(to);
			messageHelper.setSubject(subject);
			// 抄送
			// mimeMessageHelper.addCc("抄送");
			messageHelper.setText(content, true);
			javaMailSender.send(mimeMailMessage);
		} catch (Exception e) {
			logger.error("邮件发送失败", e.getMessage());
		}
	}

	/**
	 * 发送带附件格式的邮件
	 *
	 * @param mailBean
	 */
	public void sendAttachmentMail(String to, String subject, String content) {
		MimeMessage mimeMailMessage = null;
		try {
			mimeMailMessage = javaMailSender.createMimeMessage();
			// true 表示需要创建一个multipart message
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMailMessage, true);
			messageHelper.setFrom(MAIL_SENDER);
			messageHelper.setTo(to);
			messageHelper.setSubject(subject);
			messageHelper.setText(content);
			// 文件路径 目前写死在代码中，之后可以当参数传过来，或者在MailBean中添加属性absolutePath
			String absolutePath = "E:\\baidu.png";
			FileSystemResource file = new FileSystemResource(new File(absolutePath));
			// FileSystemResource file = new FileSystemResource(new
			// File("src/main/resources/static/image/email.png"));
			String fileName = absolutePath.substring(absolutePath.lastIndexOf(File.separator));
			// 添加附件,第一个参数表示添加到 Email 中附件的名称，第二个参数是图片资源
			messageHelper.addAttachment(fileName, file);
			// 多个附件
			// mimeMessageHelper.addAttachment(fileName1, file1);
			javaMailSender.send(mimeMailMessage);
		} catch (Exception e) {
			logger.error("邮件发送失败", e.getMessage());
		}
	}

	/**
	 * 发送带静态资源的邮件
	 *
	 * @param mailBean
	 */
	public void sendInlineMail(String to, String subject, String content) {
		MimeMessage mimeMailMessage = null;
		try {
			mimeMailMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mssageHelper = new MimeMessageHelper(mimeMailMessage, true);
			mssageHelper.setFrom(MAIL_SENDER);
			mssageHelper.setTo(to);
			mssageHelper.setSubject(subject);
			mssageHelper.setText(content, true);
			// 文件路径
			String absolutePath = "E:\\baidu.png";
			FileSystemResource file = new FileSystemResource(new File(absolutePath));
			// FileSystemResource file = new FileSystemResource(new
			// File("src/main/resources/static/image/email.png"))
			// 添加多个图片可以使用多条 <img src='cid:" + rscId + "' > 和
			// mimeMessageHelper.addInline(rscId, res) 来实现
			mssageHelper.addInline("picture", file);
			javaMailSender.send(mimeMailMessage);
		} catch (Exception e) {
			logger.error("邮件发送失败", e.getMessage());
		}
	}
}
