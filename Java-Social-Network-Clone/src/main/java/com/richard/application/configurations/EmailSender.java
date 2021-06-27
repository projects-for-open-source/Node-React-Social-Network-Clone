/**
 * Nickname: Richard
 * Email: richardvu.work@gmail.com
 * Skype: richardvu.work@gmail.com
 * Phone: (+84) 0935710974 - (+84) 0935810974
 * Country: Viet Nam
 * Year: 2021
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author Richard
 */
@Service
public class EmailSender {
	
	/**
	 * javaMailSender - EmailSender.java - JavaMailSender:
	 */
	@Autowired
	private JavaMailSender javaMailSender;
	
	/**
	 * EmailSender
	 *
	 * sendEmail
	 *
	 * @param email
	 * @param subject
	 * @param body
	 */
	public void sendEmail(String email, String subject, String body) {
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		
		msg.setSubject(subject);
		msg.setText(body);
		
		javaMailSender.send(msg);
		
	}
	
}