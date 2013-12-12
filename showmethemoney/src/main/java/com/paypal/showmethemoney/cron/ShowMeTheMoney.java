package com.paypal.showmethemoney.cron;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.utils.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.paypal.showmethemoney.dao.OfferInfo;
import com.paypal.showmethemoney.dao.UserInfo;
import com.paypal.showmethemoney.dto.OfferData;
import com.paypal.showmethemoney.service.MailingService;
import com.paypal.showmethemoney.service.MailingService.MailInfo;
import com.paypal.showmethemoney.service.UserInformationService;



@Component
public class ShowMeTheMoney {

	private final UserInformationService userInfoService;
	
	private final MailingService mailService;

	public ShowMeTheMoney()
	{
		this(null,null);
	}
	

	public ShowMeTheMoney(UserInformationService userInfoService,MailingService mailService)
	{
		this.userInfoService = userInfoService;
		this.mailService = mailService;
	}
	
	@Scheduled(cron = "01 * * * * *")
	public void execute()
	{
		System.out.println("Cron being exeucted"+DateUtils.formatDate(new Date()));
//		List<UserInfo> subscribers = subscriptionService.getAllSubscribers();
//		
//		Map<String,OfferInfo> zipCodeOfferInfo = findOfferInfo(extractZipCodes(subscribers));
//				
//		mailOfferInfo(subscribers,zipCodeOfferInfo);		
	}

	private void mailOfferInfo(List<UserInfo> users,Map<String,OfferData> zipCodeOfferInfo) 
	{
		
		List<MailInfo> mailInfos = Lists.newArrayList();
		
		for(Entry<String,OfferData> zipCodOffer:zipCodeOfferInfo.entrySet())
		{
			MailInfo mailInfo = new MailInfo();
			mailInfo.subject = "Your daily Offer info";
			mailInfo.body = zipCodOffer.getValue().getDescription();
		}
		
	}

	
	private Map<String,OfferInfo> findOfferInfo(List<String> zipCodes)
	{
		return null;
	}

	private List<String> extractZipCodes(List<UserInfo> users)
	{
		List<String> zipCodes = Lists.newArrayList();
		
		for(UserInfo user: users)
		{
			zipCodes.add(user.getZipcode());
		}
		return zipCodes;
	}
	
	
	/*
	 * 1. using mongodaoimpl.getAllUserInfo to get ALL zipcode-List<user> info.
	 * 2. using all zipcodes in step-1, get ALL offerInfo. (using StoreLocatorAPI). create map. zipcode-list<offerInfo>.
	 * 3. create differences. (using Mongo). update Mongo. get offerInfo from MONGO. create new map zipcode-list<offerData>
	 * 4. join map created in step-1 and step-3.
	 * 5. send email
	 */
}
