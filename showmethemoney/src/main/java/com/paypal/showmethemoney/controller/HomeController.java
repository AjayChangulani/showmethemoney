package com.paypal.showmethemoney.controller;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.paypal.showmethemoney.dao.MongoDaoImpl;
import com.paypal.showmethemoney.dao.UserInfo;


 
@Controller
@RequestMapping("/")
public class HomeController 
{
	@Autowired
	MongoDaoImpl mongoDaoImpl;
 
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String welcome(ModelMap model)
	{
 
		model.addAttribute("message", "Maven Web Project + Spring 3 MVC - welcome()");
 
		//Spring uses InternalResourceViewResolver and return back index.jsp
		return "index";
 
	}

	@RequestMapping(value="/unsubscribe",method=RequestMethod.GET)
	public String unsubscribePage(ModelMap model)
	{
		return "unsubscribe";
	}
	
	@RequestMapping(value="/unsubscribe", method = RequestMethod.POST)
	public String unsubscribe(@RequestParam("email") String email, @RequestParam("zip") String zip, ModelMap model) throws UnknownHostException
	{
		Optional<UserInfo> userInfo = mongoDaoImpl.getUserInfo(zip);
		if(userInfo.isPresent())
		{
			String normalizedEmail = email.endsWith("@paypal.com") ? email.replace("@paypal.com", ""):email;
			userInfo.get().getEmailList().remove(normalizedEmail);
		
			mongoDaoImpl.saveUserInfo(userInfo.get());
		}
		model.put("email", email);
		model.put("zip", zip);
		
		return "unsubscribeGreetings";
	}	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public String signup(@RequestParam("email") String email, @RequestParam("zip") String zip, ModelMap model) throws UnknownHostException
	{
		/*
		 * TO-DO: check for paypal email and 5 digit numeric zipcode.
		 */
		
		String normalizedEmail = email.endsWith("@paypal.com") ? email.replace("@paypal.com", ""):email;
		
		model.addAttribute("email", normalizedEmail);
		model.addAttribute("zip", zip);
		
		System.out.println("ZIPCDEEEEE:" + zip);
		handleUser(email, zip);
		
		return "congratulations";
	}
	


	private void handleUser(String email, String zip) throws UnknownHostException
	{
		/*
		 * TO-DO: Handle duplicate email
		 */
		Optional<ImmutableList<String>> emails = mongoDaoImpl.getAllEmailAddresses(zip);
		
		List<String> emailList = new ArrayList<String>();
		
		if(emails.isPresent())
		{
			emailList.addAll(emails.get());
			
		}
		
		emailList.add(email);
		
		UserInfo userInfo = new UserInfo();
		userInfo.setEmailList(emailList);
		userInfo.setZipcode(zip);
		
		System.out.println("handleUser-TEST:"+userInfo);
		mongoDaoImpl.saveUserInfo(userInfo);
	}
	
//	@RequestMapping(value="/welcome/{name}", method = RequestMethod.GET)
//	public String welcomeName(@PathVariable String name, ModelMap model) {
// 
//		model.addAttribute("message", "Maven Web Project + Spring 3 MVC - " + name);
//		return "index";
// 
//	}
}