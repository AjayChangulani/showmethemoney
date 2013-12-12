package com.paypal.showmethemoney.config;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.annotation.Configuration;

import com.google.common.collect.ImmutableList;
import com.paypal.showmethemoney.dto.OfferData;

@Configuration
public class EmailConfig
{
	private static String username = "corp" + "\\" + "achangulani";
	private static String password = "paypal1028#";
	private static String smtp_host = "smtplvs.qa.paypal.com";
	private static String smtp_port = "25";
	private static String email_from = "achangulani@corp.ebay.com";
	
	private static Session session;
	
	static
	{
		Properties props = new Properties();
		props.put("mail.smtp.host", smtp_host);
		props.put("mail.smtp.port", smtp_port);
		props.put("mail.smtp.socketFactory.port", smtp_port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
 
		session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
	}

	
	public static Session getSession()
	{
		return session;
	}
	
	public static Message getMessage(ImmutableList<String> recipients, ImmutableList<OfferData> offerData) throws AddressException, MessagingException
	{
		String text = "<h1>Howdy PayPalians<h1>" + "<img src=\"https://www.paypalobjects.com/webstatic/i/sparta/logo/logo_paypal_106x29.png\"></img>";
				
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(email_from));
		for(String recipient:recipients)
		{
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
		}
		message.setSubject("Offer");
		//create String text for offerBody using offerInfo
		message.setText(text);
		message.setContent(text, "text/html");
		
		return message;
	}
	
	public static void sendEmail(Message message) throws MessagingException
	{
		Transport.send(message);
	}
}
