package com.paypal.showmethemoney.service;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.paypal.showmethemoney.dto.CM2OfferData;

public interface Facade {

	// Map<String Zipcode, List<CM2OfferData>> findOfferData(List<String> zipCodes);

	ImmutableMap<String,ImmutableList<CM2OfferData>> findOfferData(List<String> zipCodes);

}
/*
 * (1) Optional<OfferInfo> OfferInfoRetreivalServiceImpl.getAllOffersForZipCode(zipcode)
 * (2) Calculate Difference. (+/-)
 * (3) Save list of (difference) paypalIds to zip_offers collection.
 * (4) FOR EACH paypalId: 
 * 		(4a) get CM2OfferData CM2ServiceImpl.getOfferDataFromCM2(offerId).
 * (5) create map for <receipients>, <cm2offerdata>.
 * (6) email using EmailConfig.getMessage(ImmutableList<String> recipients, ImmutableList<CM2OfferData> offerData). Transport.send(message).
 */
