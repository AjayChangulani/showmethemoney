package com.paypal.showmethemoney.service;

import java.io.IOException;

import com.google.common.base.Optional;
import com.paypal.showmethemoney.dto.CM2OfferData;

public interface CM2Service
{
	Optional<CM2OfferData> getOfferDataFromCM2(String paypal_id) throws IOException;
}
