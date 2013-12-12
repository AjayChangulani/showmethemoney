package com.paypal.showmethemoney.service;

import java.net.URI;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;

@Service
public class HttpServiceImpl implements HttpService
{
	private static final String CM2_KEY_QA_ENV = "8f677dd34ae7f3fe0ff99d647d4b0684";
	private static final String CM2_SECRET_QA_ENV = "a5a1ad4e0f1182cf26c053c01d30bdf5";
	public final static String CONTENT_TYPE_JSON_UTF8 = "application/json; charset=utf-8";

	
	
	public HttpResponse getCM2OfferData(String offer_id)
	{
		if(offer_id == null)
		{
			throw new IllegalArgumentException("URL is null");
		}
		try
		{


			String url = new StringBuilder()
										.append("https://cm.qa.where.com/api/v3/products/")
										.append(offer_id)
										.append(".json")
										.append(getAuthenticationQueryParameters())
										.append("&include=nested").toString();

			
			HttpClient client = new DefaultHttpClient();
			
			HttpGet get = new HttpGet(new URI(url));
			get.addHeader("Accept", CONTENT_TYPE_JSON_UTF8);

			HttpResponse response = client.execute(get);
			
			return response;
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	private static String getAuthenticationQueryParameters()
	{
		long currentTime = new Date().getTime();

		return String.format("?t=%1$s&key=%2$s&token=%3$s",
				currentTime,
				CM2_KEY_QA_ENV,
				DigestUtils.shaHex(CM2_SECRET_QA_ENV + currentTime));
	}
	
	
}
