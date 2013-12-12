package com.paypal.showmethemoney.dao;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties({ "_id" })
public class OfferInfo
{
	@JsonProperty("zipcode")
	private String zipcode;
	
	@JsonProperty("paypalIdList")
	private List<String> paypalIdList;
	
	public List<String> getPaypalIdList()
	{
		return paypalIdList;
	}

	public void setPaypalIdList(List<String> paypalIdList)
	{
		this.paypalIdList = paypalIdList;
	}

	public String getZipcode()
	{
		return zipcode;
	}

	public void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}

	

}
