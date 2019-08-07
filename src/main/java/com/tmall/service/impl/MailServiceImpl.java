package com.tmall.service.impl;

import org.springframework.stereotype.Service;

import com.tmall.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Override
	public void sendMail() {
		String to = null;
		String subject = null;
		String content = null;
//		sendHtmlMail(to,subject,content);
	}

}
