package com.paypal.showmethemoney.service;

import org.apache.http.HttpResponse;

public interface HttpService
{
	HttpResponse getCM2OfferData(String offer_id);
}
