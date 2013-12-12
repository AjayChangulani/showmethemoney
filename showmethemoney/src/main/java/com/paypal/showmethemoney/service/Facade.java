package com.paypal.showmethemoney.service;

import java.util.List;
import java.util.Map;

import com.paypal.showmethemoney.dto.CM2OfferData;

public interface Facade {

	Map<String,List<CM2OfferData>> findOfferData(List<String> zipCodes);

}
