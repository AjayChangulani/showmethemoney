package com.paypal.showmethemoney.service;

import java.util.List;
import java.util.Map;

import com.paypal.showmethemoney.dto.CM2OfferData;

public interface OfferDataRetreivalService {

	Map<String,CM2OfferData> findOfferInfo(List<String> zipCodes);

}
