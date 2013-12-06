package com.paypal.showmethemoney.dao;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties({ "_id" })
public class UserInfo
{
	@JsonProperty("zipcode")
	private String zipcode;
	
	@JsonProperty("emailList")
	private List<String> emailList;
	
	public String getZipcode()
	{
		return zipcode;
	}

	public void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}

	public List<String> getEmailList()
	{
		return emailList;
	}

	public void setEmailList(List<String> email)
	{
		this.emailList = email;
	}
}
