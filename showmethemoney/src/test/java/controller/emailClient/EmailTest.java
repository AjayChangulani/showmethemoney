package controller.emailClient;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class EmailTest
{

//	@Test
//	public void test()
//	{
//		final Email email = new Email();
//
//		String username = "corp" + "\\" + "achangulani";
//		email.setFromAddress("Test", "achangulani@paypal.com");
//		email.setSubject("TEST");
//		email.addRecipient("Test", "achangulani@paypal.com", RecipientType.TO);
//		email.setText("TEST");
//		
//		
//		new Mailer("10.245.17.13", 53, username, "paypal1028#",  TransportStrategy.SMTP_SSL).sendMail(email);
//	}
//	
	@Test
	@Ignore
	public void javamail()
	{
		final String username = "corp" + "\\" + "achangulani";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtplvs.qa.paypal.com");
		props.put("mail.smtp.port", "25");
		
		props.put("mail.smtp.socketFactory.port", "25");
		
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");

//		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.ssl.enable", "true");
//		props.put("mail.smtp.ssl.trust", "molecule.corp.ebay.com");
		
		
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,"paypal1028#");
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("achangulani@corp.ebay.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("achangulani@corp.ebay.com"));
			message.setSubject("New offer");
			
			String text = "<h1>Howdy PayPalians<h1>" + "<img src=\"https://www.paypalobjects.com/webstatic/i/sparta/logo/logo_paypal_106x29.png\"></img>";
			
			message.setText(text);
			message.setContent(text, "text/html");
			
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
