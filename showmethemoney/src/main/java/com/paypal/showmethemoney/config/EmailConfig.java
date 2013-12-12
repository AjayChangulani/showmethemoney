package com.paypal.showmethemoney.config;

import javax.mail.internet.MimeMessage.RecipientType;

import org.codemonkey.simplejavamail.Email;
import org.codemonkey.simplejavamail.Mailer;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.ImmutableList;
import com.paypal.showmethemoney.dto.CM2OfferData;

@Configuration
public class EmailConfig
{
	private static String username = "corp" + "\\" + "achangulani";
	private static String password = "paypal1028#";
	private static String smtp_host = "smtplvs.qa.paypal.com";
	private static Integer smtp_port = 25;
	private static String email_from = "achangulani@corp.ebay.com";
	private static String email_subject = "ShowMeTheMoney!";
	
	private static Mailer mailer;
	
	private static final String email_body_part_beginning = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\"> <head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> <title>PayPal Offer</title> </head><body bgcolor=\"#8d8e90\"> <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#8d8e90\"> <tr> <td><table width=\"600\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#FFFFFF\" align=\"center\"> <tr> <td><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"393\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td height=\"46\" align=\"right\" valign=\"middle\"> <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"4%\">&nbsp;</td> </tr> </table> </td> </tr> </table> </td> </tr> </table></td> </tr> <tr> <td align=\"center\" valign=\"middle\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"2%\">&nbsp;</td> <td width=\"96%\" align=\"center\" style=\"border-bottom:1px solid #000000\" height=\"70\"><font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#68696a; font-size:46px; text-transform:uppercase\"><strong>NEW OFFER!!! YAYY!!!</strong></font></td> <td width=\"2%\">&nbsp;</td> </tr> </table></td> </tr> <tr> <td>&nbsp;</td> </tr> <tr> <td><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"5%\">&nbsp;</td> <td width=\"90%\" align=\"center\" valign=\"middle\"><font style=\"font-family: Verdana, Geneva, sans-serif; color:#68696a; font-size:12px; line-height:20px\">Howdy Paypalian - below offers were just created in zipcode {zipCode} <br/> SAVE THEM TO YOUR PAYPAL WALLET <strong>NOW</strong></font> <br /> </td><td width=\"5%\">&nbsp;</td> </tr> </table></td> </tr> <tr> <td>&nbsp;</td> </tr> <tr> <td><table width=\"600\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr><td width=\"18\">&nbsp;</td>";
	private static final String email_body_part_end = "</tr> </table></td></tr> <tr> <td>&nbsp;</td> </tr> <tr> <td>&nbsp;</td> </tr> <tr> <td><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td width=\"14%\" align=\"center\"><font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#010203; font-size:9px; text-transform:uppercase\"><a href= \"http://localhost:9993/showmethemoney/unsubscribe\" style=\"color:#010203; text-decoration:none\"><strong>UNSUBSCRIBE </strong></a></font></td> </tr> </table></td> </tr> <tr> <td>&nbsp;</td> </tr> <tr> <td align=\"center\"><font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#231f20; font-size:8px\"><strong>Hackathon Project | Developed by: Ajay Changulani &amp; Abhijeet Kushe</strong></font></td> </tr> <tr> <td>&nbsp;</td> </tr> </table></td> </tr> </table> </body> </html>";
	
	private static String email_body_part_mid = "<td width=\"175\" align=\"center\" valign=\"top\"> <table width=\"175\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td  bgcolor=\"#0079C1\"><p width=\"175\" height=\"14\" style=\"display:block\" border=\"0\"></p></td> </tr> <tr> <td height=\"30\" align=\"center\" valign=\"middle\" bgcolor=\"#0079C1\"><font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#ffffff; font-size:20px; text-transform:uppercase\"><strong>%s</strong></font></td> </tr> <tr> <td bgcolor=\"#0079C1\"><p width=\"175\" height=\"18\"></p></td> </tr> <tr> <td align=\"center\" valign=\"middle\" bgcolor=\"#0079C1\"> <font style=\"font-family:'Myriad Pro', Helvetica, Arial, sans-serif; color:#ffffff; font-size:14px\"> <strong>%s</strong> </font> </td> </tr> <tr> <td align=\"center\" valign=\"middle\" bgcolor=\"#0079C1\">&nbsp;</td> </tr> </table> </td><td width=\"19\">&nbsp;</td>";
	static
	{
		mailer = new Mailer(smtp_host, smtp_port, username, password);
	}

	public static void sendEmail(ImmutableList<String> emailIds, ImmutableList<CM2OfferData> offerData, String zip)
	{
		final Email email = new Email();

		email.setFromAddress("Paypalian", email_from);
		email.setSubject(email_subject);
		
		for(String emailId : emailIds)
		{
			emailId += "@corp.ebay.com";
			email.addRecipient("Paypalian", emailId, RecipientType.TO);
		}
		
		email.setTextHTML(getEmailBody(offerData, zip));
		
		mailer.sendMail(email);
	}
	/*
	 * Only show 3 offers
	 */
	private static String getEmailBody(ImmutableList<CM2OfferData> offersData, String zip)
	{
		StringBuilder sb = new StringBuilder();
		sb.append((email_body_part_beginning.replace("{zipCode}", zip)));
		
		int i=1;
		for(CM2OfferData offerData : offersData)
		{
			if(i<4)
			{
				++i;
				sb.append(String.format(email_body_part_mid, offerData.getTitle(), offerData.getMerchant_name()));
			}
			else
			{
				break;
			}
		}
		
		sb.append(email_body_part_end);
		
		return sb.toString();
	}

}
