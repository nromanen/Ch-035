package com.crsms.service;

import javax.mail.MessagingException;

public interface MailService {
	void send(String recipientEmail, String subject, String text, Boolean html)
			throws MessagingException;
	
	void sendSimpleEmail(String recipientEmail, String subject, String text)
			throws MessagingException;
	 
	void sendHtmlEmail(String recipientEmail, String subject, String text)
			throws MessagingException;
	
	void sendInvitation(String recipientEmail) throws MessagingException;
	
	void sendConfirmation(String recipientEmail, long id) throws MessagingException;
}
