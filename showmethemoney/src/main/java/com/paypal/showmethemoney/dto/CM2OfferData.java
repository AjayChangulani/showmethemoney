package com.paypal.showmethemoney.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CM2OfferData {

	@Override
	public String toString()
	{
		return "CM2OfferData [title=" + title + ", merchant_name=" + merchant_name + "]";
	}

	@JsonProperty("title")
	private String title;
	
	@JsonProperty("merchant_name")
	private String merchant_name;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getMerchant_name()
	{
		return merchant_name;
	}

	public void setMerchant_name(String merchant_name)
	{
		this.merchant_name = merchant_name;
	}
}
