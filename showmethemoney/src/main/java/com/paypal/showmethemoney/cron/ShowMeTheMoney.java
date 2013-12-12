package com.paypal.showmethemoney.cron;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.paypal.showmethemoney.config.EmailConfig;
import com.paypal.showmethemoney.dao.UserInfo;
import com.paypal.showmethemoney.dto.CM2OfferData;
import com.paypal.showmethemoney.service.Facade;
import com.paypal.showmethemoney.service.MailingService;
import com.paypal.showmethemoney.service.UserInformationService;


@Component
public class ShowMeTheMoney {

	private final UserInformationService userInfoService;
	
	private final MailingService mailService;

//	@Autowired
//	Facade facade;
//	
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
	public void execute() throws UnknownHostException, AddressException, MessagingException
	{
		System.out.println("Cron being exeucted: "+DateUtils.formatDate(new Date()));

		
//		ImmutableList<UserInfo> allUsers = userInfoService.getAllUsersInformation();
//		ImmutableMap.Builder<String, ImmutableList<String>> zip_emailIds_map_builder = ImmutableMap.<String, ImmutableList<String>> builder();
//
//		/*
//		 * TO-DO:tranfer this to mongodaoimpl
//		 */
//		for(UserInfo individualUser:allUsers)
//		{
//			String zip = individualUser.getZipcode();
//			ImmutableList<String> emailIds = (ImmutableList<String>) individualUser.getEmailList();
//			zip_emailIds_map_builder.put(zip, emailIds);
//		}
//		
//		ImmutableMap<String, ImmutableList<String>> zip_emailIds_map = zip_emailIds_map_builder.build();
//		
//		ImmutableMap<String, ImmutableList<CM2OfferData>> zip_offerData_map = facade.findOfferData(extractZipCodes(allUsers));
//		
//		ImmutableMap.Builder<ImmutableList<String>, ImmutableList<CM2OfferData>> emailIds_offerData_map_builder = ImmutableMap.<ImmutableList<String>, ImmutableList<CM2OfferData>> builder();
//		
//		for(Entry<String, ImmutableList<String>> entry : zip_emailIds_map.entrySet())
//		{
//			String zip = entry.getKey();
//			ImmutableList<String> emailIds = entry.getValue();
//			
//			ImmutableList<CM2OfferData> offers = zip_offerData_map.get(zip);
//			
//			emailIds_offerData_map_builder.put(emailIds, offers);
//		}
//
//		ImmutableMap<ImmutableList<String>, ImmutableList<CM2OfferData>> emailIds_offerData_map = emailIds_offerData_map_builder.build();
//		
//		for(Entry<ImmutableList<String>, ImmutableList<CM2OfferData>> entry : emailIds_offerData_map.entrySet())
//		{
//			sendEmail(entry.getKey(), entry.getValue());
//		}
	}

	private void sendEmail(ImmutableList<String> recipients, ImmutableList<CM2OfferData> offers) throws AddressException, MessagingException
	{
		/*
		 * TO-DO:transfer this to separate EmailService
		 */
		Message message = EmailConfig.getMessage(recipients, offers);
		EmailConfig.sendEmail(message);
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
	 * 2. for each zipcode in step-1, get ALL offerIds in that zipcode (using StoreLocatorAPI). create map. zipcode-list<offerInfo>.
	 * 3. 	(A) create differences. (what are the new offers or what offers no longer exists -- using Mongo). 
	 * 	  	(B) update Mongo(zip_offer collection). --> 
	 * 		(C) get offer_id from MONGO(qa) -> DONE 
	 * 		(D) get CM2OfferData object from CM2 based on offer_id in (C). -> DONE
	 * 		(E) create new map zipcode-list<CM2OfferData>
	 * 4. join map created in step-1 and step-3. email,list<CM2OfferData>
	 * 5. send email
	 */
}
