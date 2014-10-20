package com.starbattle.accounts.mail;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailService {

	public MailService() {
		// TODO Auto-generated constructor stub
	}

	public static boolean sendMail(String to, String user, String newPassword) {
		// Sender's email ID needs to be mentioned
		String from = "game.starbattle@gmail.com";

		// Assuming you are sending email from localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.starttls.required", "true");
		
		properties.put("mail.smtp.user", "game.starbattle");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.debug", "true");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentification() {
						return new PasswordAuthentication("game.starbattle",
								"dhbwbattle");
					}
				});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			// Set Subject: header field
			message.setSubject("StarBattle: New Password");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText("Dear " + user + ", your new password is: "
					+ newPassword + " Sincerly your StarBattle-Team. ");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport tr = session.getTransport("smtp");
			tr.connect("smtp.gmail.com", "game.starbattle", "dhbwstar");
			message.saveChanges();
			tr.sendMessage(message, message.getAllRecipients());

			return true;
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
	}

}
