package com.paypal.showmethemoney.service;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.paypal.showmethemoney.dto.CM2OfferData;

public class CM2ServiceImpl implements CM2Service
{
	@Autowired
	HttpServiceImpl httpService;
	
	ObjectMapper objMapper;
	
	public CM2ServiceImpl(HttpServiceImpl httpService)
	{
		this.httpService = httpService;
		objMapper = new ObjectMapper();
	}
	
	public CM2OfferData getOfferDataFromCM2(String offer_id) throws IllegalArgumentException, IllegalStateException, IOException
	{
		HttpResponse response = httpService.getCM2OfferData(offer_id);
		CM2OfferData cm2OfferData = objMapper.readValue(response.getEntity().getContent(), CM2OfferData.class);
		return cm2OfferData;
	}

}
