package com.paypal.showmethemoney.cron;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.paypal.showmethemoney.dao.UserInfo;
import com.paypal.showmethemoney.service.MailingService;
import com.paypal.showmethemoney.service.UserInformationService;
import com.paypal.showmethemoney.dto.CM2OfferData;


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

//	private void mailOfferInfo(List<UserInfo> users,Map<String,OfferData> zipCodeOfferInfo) 
//	{
//		
//		List<MailInfo> mailInfos = Lists.newArrayList();
//		
//		for(Entry<String,OfferData> zipCodOffer:zipCodeOfferInfo.entrySet())
//		{
//			MailInfo mailInfo = new MailInfo();
//			mailInfo.subject = "Your daily Offer info";
//			mailInfo.body = zipCodOffer.getValue().getDescription();
//		}
//		
//	}

	
	private Map<String,CM2OfferData> findOfferInfo(List<String> zipCodes)
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
	 * 1. using mongodaoimpl.getAllUserInfo to get ALL zipcode-List<user> info. DONE
	 * 2. using all zipcodes in step-1, get ALL offerIds in that zipcode (using StoreLocatorAPI). create map. zipcode-list<offerInfo>.
	 * 3. 	(A) create differences. (what are the new offers or what offers no longer exists -- using Mongo). 
	 * 	  	(B) update Mongo(zip_offer collection). --> 
	 * 		(C) get offer_id from MONGO(qa) -> DONE 
	 * 		(D) get CM2OfferData object from CM2 based on offer_id in (C). -> DONE
	 * 		(E) create new map zipcode-list<offerData>
	 * 4. join map created in step-1 and step-3.
	 * 5. send email
	 */
}
