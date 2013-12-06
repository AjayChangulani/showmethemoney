package com.paypal.showmethemoney.service;

import java.util.List;

public interface MailingService {

	public class MailInfo
	{
		public String subject;
		public String body;
		public List<String> emailIds;
	}
	
	void mail(MailInfo info);
	
}
