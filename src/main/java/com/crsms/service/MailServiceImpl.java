package com.crsms.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
	private static final String SENDER_EMAIL = "ss.crsms@gmail.com";
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final Logger logger = LogManager.getLogger(MailServiceImpl.class);
	
	private ExecutorService executorService = Executors.newFixedThreadPool(1);
	
	@Override
	public void send(String recipientEmail, String subject, String text, Boolean html)
			throws MessagingException {
		
		MimeMessage message = createMessage(recipientEmail, subject, text, html);
		sendMessage(message, recipientEmail);
	}
	
	@Override
	public void sendSimpleEmail(String recipientEmail, String subject, String plainText)
			throws MessagingException {
		send(recipientEmail, subject, plainText, false);
	}
	
	@Override
	public void sendHtmlEmail(String recipientEmail, String subject, String htmlText)
			throws MessagingException {
		send(recipientEmail, subject, htmlText, true);
	}

	@Override
	public void sendInvitation(String recipientEmail) throws MessagingException {
		String subject = "Invitation to the CrsMS!";
		String text = "Welcome, dude!";
		sendSimpleEmail(recipientEmail, subject, text);
	}
	
	private MimeMessage createMessage(String recipientEmail, String subject, String text,
			Boolean html) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom(SENDER_EMAIL);
			helper.setTo(recipientEmail);
			helper.setSubject(subject);
			helper.setText(text, html);
		} catch (MessagingException e) {
			logger.error("Error in setting email parameters. Reason: " + e);
			throw e;
		}
		return message;
	}

	@Override
	public void sendConfirmation(String recipientEmail, long id)
			throws MessagingException {
		String subject = "Confirm registration";
//		String encId = passwordEncoder.encode(String.valueOf(id));
		String text = "<h3>Welcome to CrsMS  !</h3><br>"
						+ "Thank you for registering! <br> Please click on the "
						+ "confirmation link below<br>"
						+ "<a href ="
						+ "\"http://localhost:8080/crsms/user/"+id+"/activated\">"
						+ "Click here</a>";
			
		sendHtmlEmail(recipientEmail, subject, text);
		
	}


	private void sendMessage(final MimeMessage message, final String recipientEmail) {
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					logger.info("Sending email to " + recipientEmail);
					mailSender.send(message);
					logger.info("Email to " + recipientEmail + " has been successfully sent.");
				} catch (Exception e) {
					logger.error("Error in sending email. Reason: " + e);
					throw e;
				}
			}
		});
	}
}
