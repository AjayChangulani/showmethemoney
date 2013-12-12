package com.paypal.showmethemoney.cron;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

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
import com.paypal.showmethemoney.dao.MongoDaoImpl;
import com.paypal.showmethemoney.dao.UserInfo;
import com.paypal.showmethemoney.dto.CM2OfferData;
import com.paypal.showmethemoney.service.CM2ServiceImpl;
import com.paypal.showmethemoney.service.Facade;
import com.paypal.showmethemoney.service.FacadeImpl;
import com.paypal.showmethemoney.service.HttpServiceImpl;
import com.paypal.showmethemoney.service.OfferInfoRetreivalServiceImpl;
import com.paypal.showmethemoney.service.UserInformationService;
import com.paypal.showmethemoney.service.UserInformationServiceImpl;


@Component
public class ShowMeTheMoney {

	private final UserInformationService userInfoService;
	
	Facade facade;
	
	@Autowired
	public ShowMeTheMoney(UserInformationService userInfoService, Facade facade)
	{
		this.userInfoService = userInfoService;
		this.facade = facade;
	}
	
	@Scheduled(cron = "02 * * * * *")
	public void execute() throws UnknownHostException, AddressException, MessagingException
	{
		System.out.println("Cron being exeucted: "+DateUtils.formatDate(new Date()));

		
		ImmutableList<UserInfo> allUsers = userInfoService.getAllUsersInformation();
		ImmutableMap.Builder<String, ImmutableList<String>> zip_emailIds_map_builder = ImmutableMap.<String, ImmutableList<String>> builder();

		/*
		 * TO-DO:tranfer this to mongodaoimpl
		 */
		
		for(UserInfo individualUser : allUsers)
		{
			String zip = individualUser.getZipcode();
			
			ImmutableList<String> emailIds = ImmutableList.copyOf(individualUser.getEmailList());
			zip_emailIds_map_builder.put(zip, emailIds);
		}
		
		ImmutableMap<String, ImmutableList<String>> zip_emailIds_map = zip_emailIds_map_builder.build();
		
		ImmutableMap<String, ImmutableList<CM2OfferData>> zip_offerData_map = facade.findOfferData(extractZipCodes(allUsers));
		
		ImmutableMap.Builder<ImmutableList<String>, ImmutableList<CM2OfferData>> emailIds_offerData_map_builder = ImmutableMap.<ImmutableList<String>, ImmutableList<CM2OfferData>> builder();
		
		for(Entry<String, ImmutableList<String>> entry : zip_emailIds_map.entrySet())
		{
//			ImmutableMap.Builder<ImmutableList<String>, ImmutableList<CM2OfferData>> emailIds_offerData_map_builder = ImmutableMap.<ImmutableList<String>, ImmutableList<CM2OfferData>> builder();
			
			String zip = entry.getKey();
			ImmutableList<String> emailIds = entry.getValue();
			
			ImmutableList<CM2OfferData> offers = zip_offerData_map.get(zip);
			
			if(!offers.isEmpty())
			{
//				emailIds_offerData_map_builder.put(emailIds, offers);
				sendEmail(emailIds, offers, zip);
			}
		}

//		ImmutableMap<ImmutableList<String>, ImmutableList<CM2OfferData>> emailIds_offerData_map = emailIds_offerData_map_builder.build();
		
		
//		for(Entry<ImmutableList<String>, ImmutableList<CM2OfferData>> entry : emailIds_offerData_map.entrySet())
//		{
//			sendEmail(entry.getKey(), entry.getValue());
//		}
		
		System.out.println("Cron Job Finished." + new Date().getTime());
	}

	private void sendEmail(ImmutableList<String> recipients, ImmutableList<CM2OfferData> offers, String zip) throws AddressException, MessagingException
	{
		/*
		 * TO-DO:transfer this to separate EmailService
		 */
		System.out.println("Sending Email. Recipients:" + recipients + ". Offers:" + offers);
		
		EmailConfig.sendEmail(recipients, offers, zip);
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

	public static void main(String[] args) throws AddressException, UnknownHostException, MessagingException
	{
		MongoDaoImpl mongo = new MongoDaoImpl();
		new ShowMeTheMoney(new UserInformationServiceImpl(mongo), new FacadeImpl(new OfferInfoRetreivalServiceImpl(),new CM2ServiceImpl(new HttpServiceImpl(), mongo), mongo)).execute();
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
