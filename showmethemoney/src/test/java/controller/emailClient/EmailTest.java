package controller.emailClient;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.codemonkey.simplejavamail.Email;
import org.codemonkey.simplejavamail.Mailer;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.paypal.showmethemoney.config.EmailConfig;
import com.paypal.showmethemoney.dto.CM2OfferData;


public class EmailTest
{

String text = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\"> <head>" + 
	"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> <title>PayPal Offer</title> </head><body bgcolor=\"#8d8e90\"> <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#8d8e90\"> <tr> <td><table width=\"600\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#FFFFFF\" align=\"center\"> <tr> <td><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"393\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td height=\"46\" align=\"right\" valign=\"middle\"> <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"4%\">&nbsp;</td> </tr> </table> </td> </tr> </table> </td> </tr> </table></td> </tr> <tr> <td align=\"center\" valign=\"middle\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"2%\">&nbsp;</td> <td width=\"96%\" align=\"center\" style=\"border-bottom:1px solid #000000\" height=\"70\"><font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#68696a; font-size:46px; text-transform:uppercase\"><strong>NEW OFFER!!! YAYY!!!</strong></font></td> <td width=\"2%\">&nbsp;</td> </tr> </table></td> </tr> <tr> <td>&nbsp;</td> </tr> <tr> <td><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"5%\">&nbsp;</td> <td width=\"90%\" align=\"center\" valign=\"middle\"><font style=\"font-family: Verdana, Geneva, sans-serif; color:#68696a; font-size:12px; line-height:20px\">Howdy Paypalian - below offers were just created in zipcode 02114 <br/> SAVE THEM TO YOUR PAYPAL WALLET <strong>NOW</strong></font> <br /> </td><td width=\"5%\">&nbsp;</td> </tr> </table></td> </tr> <tr> <td>&nbsp;</td> </tr> <tr> <td><table width=\"600\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"18\">&nbsp;</td> <td width=\"175\" align=\"center\" valign=\"top\"> <table width=\"175\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td  bgcolor=\"#03bcda\"><p width=\"175\" height=\"14\" style=\"display:block\" border=\"0\"></p></td> </tr> <tr> <td height=\"30\" align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\"><font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#ffffff; font-size:20px; text-transform:uppercase\"><strong>$5 Coupon</strong></font></td> </tr> <tr> <td bgcolor=\"#03bcda\"><p width=\"175\" height=\"18\"></p></td> </tr> <tr> <td align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\"> <font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#ffffff; font-size:14px\"> <strong>EAT-24</strong> </font> </td> </tr> <tr> <td align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\">&nbsp;</td> </tr> </table> </td>"+
	"<td width=\"19\">&nbsp;</td> <td width=\"175\" align=\"center\" valign=\"top\"> <table width=\"175\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td  bgcolor=\"#03bcda\"><p width=\"175\" height=\"14\" style=\"display:block\" border=\"0\"></p></td> </tr> <tr> <td height=\"30\" align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\"><font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#ffffff; font-size:20px; text-transform:uppercase\"><strong>$5 Coupon</strong></font></td> </tr> <tr> <td bgcolor=\"#03bcda\"><p width=\"175\" height=\"18\"></p></td> </tr> <tr> <td align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\"> <font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#ffffff; font-size:14px\"> <strong>EAT-24</strong> </font> </td> </tr> <tr> <td align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\">&nbsp;</td> </tr> </table> </td> <td width=\"19\">&nbsp;</td> <td width=\"175\" align=\"center\" valign=\"top\"> <table width=\"175\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td  bgcolor=\"#03bcda\"><p width=\"175\" height=\"14\" style=\"display:block\" border=\"0\"></p></td> </tr> <tr> <td height=\"30\" align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\"><font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#ffffff; font-size:20px; text-transform:uppercase\"><strong>$10 off</strong></font></td> </tr> <tr> <td bgcolor=\"#03bcda\"><p width=\"175\" height=\"18\"></p></td> </tr> <tr> <td align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\"> <font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#ffffff; font-size:14px\"> <strong>The Home Depot</strong> </font> </td> </tr> <tr> <td align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\">&nbsp;</td> </tr> </table> </td> <td width=\"19\">&nbsp;</td> </tr> </table></td> </tr> <tr> <td>&nbsp;</td> </tr>"+
	"<tr> <td>&nbsp;</td> </tr> <tr> <td><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"14%\" align=\"center\"><font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#010203; font-size:9px; text-transform:uppercase\"><a href= \"http://yourlink\" style=\"color:#010203; text-decoration:none\"><strong>UNSUBSCRIBE </strong></a></font></td> </tr> </table></td> </tr> <tr> <td>&nbsp;</td> </tr> <tr> <td align=\"center\"><font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#231f20; font-size:8px\"><strong>Hackathon Project | Developed by: Ajay Changulani &amp; Abhijeet Kushe</strong></font></td> </tr> <tr> <td>&nbsp;</td> </tr> </table></td> </tr> </table> </body> </html>";

	@Test
	@Ignore
	public void test()
	{
		final Email email = new Email();

		String username = "corp" + "\\" + "achangulani";
		email.setFromAddress("Test", "achangulani@corp.ebay.com");
		email.setSubject("ShowMeTheMoney!");
		email.addRecipient("Test", "achangulani@corp.ebay.com", RecipientType.TO);
		email.setTextHTML(text);
		new Mailer("smtplvs.qa.paypal.com", 25, username, "paypal1028#").sendMail(email);
	}
	
	@Test
	@Ignore
	public void test2()
	{
		ImmutableList.Builder<String> emailBuilder = ImmutableList.<String> builder();
		emailBuilder.add("achangulani@corp.ebay.com");
		emailBuilder.add("akushe@corp.ebay.com");
		

		ImmutableList.Builder<CM2OfferData> offerBuilder = ImmutableList.<CM2OfferData> builder();
		
		
		CM2OfferData cm2OfferData1 = new CM2OfferData();
		cm2OfferData1.setMerchant_name("Toys R Us");
		cm2OfferData1.setTitle("$5 off $10");
		
		CM2OfferData cm2OfferData2 = new CM2OfferData();
		cm2OfferData2.setMerchant_name("Toys R Us");
		cm2OfferData2.setTitle("$5 off $10");
		

		CM2OfferData cm2OfferData3 = new CM2OfferData();
		cm2OfferData3.setMerchant_name("Toys R Us");
		cm2OfferData3.setTitle("$5 off $10");
		

		CM2OfferData cm2OfferData4 = new CM2OfferData();
		cm2OfferData4.setMerchant_name("Toys R Us");
		cm2OfferData4.setTitle("$5 off $10");
		
		
		offerBuilder.add(cm2OfferData1);
		offerBuilder.add(cm2OfferData2);
		offerBuilder.add(cm2OfferData3);
		offerBuilder.add(cm2OfferData4);
		
		EmailConfig.sendEmail(emailBuilder.build(), offerBuilder.build());
	}
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
			
//			String text = "<h1>Howdy PayPalians<h1>" + "<img src=\"https://www.paypalobjects.com/webstatic/i/sparta/logo/logo_paypal_106x29.png\"></img>";
			String text = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\"> <head>" +

			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> <title>PayPal Offer</title> </head>" +

			"<body bgcolor=\"#8d8e90\"> <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#8d8e90\"> <tr> <td><table width=\"600\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#FFFFFF\" align=\"center\"> <tr> <td><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"393\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td height=\"46\" align=\"right\" valign=\"middle\"> <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"4%\">&nbsp;</td> </tr> </table> </td> </tr> </table> </td> </tr> </table></td> </tr> <tr> <td align=\"center\" valign=\"middle\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"2%\">&nbsp;</td> <td width=\"96%\" align=\"center\" style=\"border-bottom:1px solid #000000\" height=\"70\"><font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#68696a; font-size:46px; text-transform:uppercase\"><strong>NEW OFFER!!! YAYY!!!</strong></font></td> <td width=\"2%\">&nbsp;</td> </tr> </table></td> </tr> <tr> <td>&nbsp;</td> </tr> <tr> <td><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"5%\">&nbsp;</td> <td width=\"90%\" align=\"center\" valign=\"middle\"><font style=\"font-family: Verdana, Geneva, sans-serif; color:#68696a; font-size:12px; line-height:20px\">Howdy Paypalian - below offers were just created in zipcode 02114 <br/> SAVE THEM TO YOUR PAYPAL WALLET <strong>NOW</strong></font> <br /> </td>" +


			"<td width=\"5%\">&nbsp;</td> </tr> </table></td> </tr> <tr> <td>&nbsp;</td> </tr> <tr> <td><table width=\"600\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"18\">&nbsp;</td> <td width=\"175\" align=\"center\" valign=\"top\"> <table width=\"175\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td  bgcolor=\"#03bcda\"><img src=\"\" width=\"175\" height=\"14\" style=\"display:block\" border=\"0\" alt=\"\"/></td> </tr> <tr> <td height=\"30\" align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\"><font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#ffffff; font-size:20px; text-transform:uppercase\"><strong>$5 off $10</strong></font></td> </tr> <tr> <td bgcolor=\"#03bcda\"><img src=\"\" alt=\"\" width=\"175\" height=\"18\" /></td> </tr> <tr> <td align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\"> <font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#ffffff; font-size:14px\"> <strong>Toys R Us</strong> </font> </td> </tr> <tr> <td align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\">&nbsp;</td> </tr> </table> </td>" +


			"<td width=\"19\">&nbsp;</td> <td width=\"175\" align=\"center\" valign=\"top\"> <table width=\"175\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td  bgcolor=\"#03bcda\"><img src=\"\" width=\"175\" height=\"14\" style=\"display:block\" border=\"0\" alt=\"\"/></td> </tr> <tr> <td height=\"30\" align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\"><font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#ffffff; font-size:20px; text-transform:uppercase\"><strong>$5 Coupon</strong></font></td> </tr> <tr> <td bgcolor=\"#03bcda\"><img src=\"\" alt=\"\" width=\"175\" height=\"18\" /></td> </tr> <tr> <td align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\"> <font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#ffffff; font-size:14px\"> <strong>EAT-24</strong> </font> </td> </tr> <tr> <td align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\">&nbsp;</td> </tr> </table> </td> <td width=\"19\">&nbsp;</td> <td width=\"175\" align=\"center\" valign=\"top\"> <table width=\"175\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td  bgcolor=\"#03bcda\"><img src=\"\" width=\"175\" height=\"14\" style=\"display:block\" border=\"0\" alt=\"\"/></td> </tr> <tr> <td height=\"30\" align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\"><font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#ffffff; font-size:20px; text-transform:uppercase\"><strong>$10 off</strong></font></td> </tr> <tr> <td bgcolor=\"#03bcda\"><img src=\"\" alt=\"\" width=\"175\" height=\"18\" /></td> </tr> <tr> <td align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\"> <font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#ffffff; font-size:14px\"> <strong>The Home Depot</strong> </font> </td> </tr> <tr> <td align=\"center\" valign=\"middle\" bgcolor=\"#03bcda\">&nbsp;</td> </tr> </table> </td> <td width=\"19\">&nbsp;</td> </tr> </table></td> </tr> <tr> <td>&nbsp;</td> </tr>"+

			"<tr> <td>&nbsp;</td> </tr> <tr> <td><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"14%\" align=\"center\"><font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#010203; font-size:9px; text-transform:uppercase\"><a href= \"http://yourlink\" style=\"color:#010203; text-decoration:none\"><strong>UNSUBSCRIBE </strong></a></font></td> </tr> </table></td> </tr> <tr> <td>&nbsp;</td> </tr> <tr> <td align=\"center\"><font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#231f20; font-size:8px\"><strong>Hackathon Project | Developed by: Ajay Changulani &amp; Abhijeet Kushe</strong></font></td> </tr> <tr> <td>&nbsp;</td> </tr> </table></td> </tr> </table> </body> </html>";

			
			
			
			List<String> email_ids = new ArrayList<String>();
			email_ids.add("achangulani@corp.ebay.com");
			email_ids.add("akushe@corp.ebay.com");
			
			List<CM2OfferData> offers = new ArrayList<CM2OfferData>();
			
			CM2OfferData cm2OfferData1 = new CM2OfferData();
			cm2OfferData1.setMerchant_name("Toys R Us");
			cm2OfferData1.setTitle("$5 off $10");
			
			CM2OfferData cm2OfferData2 = new CM2OfferData();
			cm2OfferData2.setMerchant_name("Toys R Us");
			cm2OfferData2.setTitle("$5 off $10");
			
			offers.add(cm2OfferData1);
			offers.add(cm2OfferData2);
			
//			message.setText(text);
			message.setContent(text, "text/html");
			
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
