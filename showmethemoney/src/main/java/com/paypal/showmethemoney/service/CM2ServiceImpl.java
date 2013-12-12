package com.paypal.showmethemoney.service;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.paypal.showmethemoney.dao.MongoDaoImpl;
import com.paypal.showmethemoney.dto.CM2OfferData;

@Service
public class CM2ServiceImpl implements CM2Service
{
	HttpServiceImpl httpService;
	MongoDaoImpl mongoDaoImpl;
	
	ObjectMapper objMapper;
	
	@Autowired
	public CM2ServiceImpl(HttpServiceImpl httpService,MongoDaoImpl mongoDaoImpl)
	{
		this.httpService = httpService;
		this.mongoDaoImpl = mongoDaoImpl;
		objMapper = new ObjectMapper();
	}
	
	public Optional<CM2OfferData> getOfferDataFromCM2(String paypal_Id) throws IOException
	{
		String offer_id = mongoDaoImpl.findOfferIdBasedOnPayPalId(paypal_Id);
		if(Strings.isNullOrEmpty(offer_id))
			return Optional.absent();
		
		HttpResponse response = httpService.getCM2OfferData(offer_id);
		CM2OfferData cm2OfferData = objMapper.readValue(response.getEntity().getContent(), CM2OfferData.class);
		
		System.out.println("Getting info from cm2:" + cm2OfferData);
		return Optional.of(cm2OfferData);
	}

}
