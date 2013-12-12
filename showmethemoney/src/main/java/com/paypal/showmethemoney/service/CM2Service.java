package com.paypal.showmethemoney.service;

import java.io.IOException;

import com.paypal.showmethemoney.dto.CM2OfferData;

public interface CM2Service
{
	CM2OfferData getOfferDataFromCM2(String paypal_id) throws IllegalArgumentException, IllegalStateException, IOException;
}
