package com.paypal.showmethemoney.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.paypal.showmethemoney.dao.MongoDao;
import com.paypal.showmethemoney.dao.OfferInfo;
import com.paypal.showmethemoney.dto.CM2OfferData;

@Service
public class FacadeImpl implements Facade
{
	private final OfferInfoRetreivalService offerInfoService;
	private final CM2Service cm2Service;
	private final MongoDao showMeTheMoneyDao;

	@Autowired
	public FacadeImpl(OfferInfoRetreivalService offerInfoService, CM2Service cm2Service, MongoDao showMeTheMoneyDao)
	{
		this.offerInfoService = offerInfoService;
		this.cm2Service = cm2Service;
		this.showMeTheMoneyDao = showMeTheMoneyDao;
	}

	public ImmutableMap<String, ImmutableList<CM2OfferData>> findOfferData(List<String> zipCodes)
	{
		ImmutableMap.Builder<String, ImmutableList<CM2OfferData>> zipCodesOfferInfo = ImmutableMap.builder();

		for(String zipCode : zipCodes)
			try
			{
				zipCodesOfferInfo.put(zipCode, getOfferInfos(zipCode));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		
		return zipCodesOfferInfo.build();
	}

	private ImmutableList<CM2OfferData> getOfferInfos(String zipCode) throws Exception
	{
		ImmutableList.Builder<CM2OfferData> cm2OfferDatas = ImmutableList.builder();

		Optional<OfferInfo> latestOfferInfo = offerInfoService.getAllOffersForZipCode(zipCode);

		if (!latestOfferInfo.isPresent())
			return ImmutableList.of();
		else
		{

			Optional<OfferInfo> existingOfferInfo = this.showMeTheMoneyDao.getOfferInfo(zipCode);
			ImmutableList<String> diffPaypalIds = differenceOrLAtest(latestOfferInfo, existingOfferInfo);

			latestOfferInfo.get().setPaypalIdList(latestOfferInfo.get().getPaypalIdList());

			showMeTheMoneyDao.saveOfferInfo(latestOfferInfo.get());

			System.out.println("printing latest paypalids:" + diffPaypalIds);
			for (String paypalId : diffPaypalIds)
			{
				System.out.println("inside latest for"+paypalId);
				Optional<CM2OfferData> offerData = cm2Service.getOfferDataFromCM2(paypalId);
				if(offerData.isPresent()) cm2OfferDatas.add(offerData.get());
			}
			return cm2OfferDatas.build();

		}

	}

	@SuppressWarnings("unchecked")
	private ImmutableList<String> differenceOrLAtest(Optional<OfferInfo> latestOfferInfo, Optional<OfferInfo> existingOfferInfo)
	{
		return ImmutableList.copyOf(
				existingOfferInfo.isPresent() ?
						CollectionUtils.subtract(latestOfferInfo.get().getPaypalIdList(), existingOfferInfo.get().getPaypalIdList()) :
						latestOfferInfo.get().getPaypalIdList());
	}
	
}
