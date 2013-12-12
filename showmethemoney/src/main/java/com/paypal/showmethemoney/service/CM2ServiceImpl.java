package com.paypal.showmethemoney.service;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.paypal.showmethemoney.dao.MongoDaoImpl;
import com.paypal.showmethemoney.dto.CM2OfferData;

public class CM2ServiceImpl implements CM2Service
{
	@Autowired
	HttpServiceImpl httpService;
	
	@Autowired
	MongoDaoImpl mongoDaoImpl;
	
	ObjectMapper objMapper;
	
	public CM2ServiceImpl(HttpServiceImpl httpService,MongoDaoImpl mongoDaoImpl)
	{
		this.httpService = httpService;
		this.mongoDaoImpl = mongoDaoImpl;
		objMapper = new ObjectMapper();
	}
	
	public CM2OfferData getOfferDataFromCM2(String paypal_Id) throws IllegalArgumentException, IllegalStateException, IOException
	{
		String offer_id = mongoDaoImpl.findOfferIdBasedOnPayPalId(paypal_Id);
		
		HttpResponse response = httpService.getCM2OfferData(offer_id);
		CM2OfferData cm2OfferData = objMapper.readValue(response.getEntity().getContent(), CM2OfferData.class);
		
		return cm2OfferData;
	}

}
