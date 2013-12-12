package com.paypal.showmethemoney.service;

import com.google.common.base.Optional;
import com.paypal.showmethemoney.dao.OfferInfo;

public interface OfferRetreivalService {

	Optional<OfferInfo> getAllOffersForZipCode(String zipcode) throws Exception;

}
