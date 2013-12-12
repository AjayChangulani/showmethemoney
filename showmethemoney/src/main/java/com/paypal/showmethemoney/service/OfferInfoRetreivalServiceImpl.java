package com.paypal.showmethemoney.service;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.paypal.showmethemoney.dao.OfferInfo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class OfferInfoRetreivalServiceImpl implements OfferInfoRetreivalService {

	private static final ClientConfig JSON_CONFIGURED = new DefaultClientConfig();

	static {
		JSON_CONFIGURED.getFeatures().put(
				JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	}

	private final Client webClient;
	
	private final String storeLocatorEndpoint = "http://storelocator.api.qa.where.com/v1/stores?zipcode=%s&radius=5.0&name=Davis";

	public OfferInfoRetreivalServiceImpl() {
		webClient = Client.create(JSON_CONFIGURED);

	}

	@Override
	public Optional<OfferInfo> getAllOffersForZipCode(String zipcode) throws Exception
	{
		JSONObject resp = webClient.resource(String.format(storeLocatorEndpoint, zipcode)).type(MediaType.APPLICATION_JSON).get(JSONObject.class);
		
		if(!resp.has("items"))
			return Optional.absent();
		
		Set<String> paypalIds = Sets.newHashSet();
		JSONArray items  =  resp.getJSONArray("items");
		for(int i = 0; i < items.length(); i++)
			paypalIds.addAll(getPaypalIds(items.getJSONObject(i)));
		
		return Optional.of(createOfferInfo(zipcode,paypalIds));
	}
	
	private OfferInfo createOfferInfo(String zipcode,Set<String> paypalIds)
	{
		OfferInfo offerInfo = new OfferInfo();
		offerInfo.setZipcode(zipcode);
		offerInfo.setPaypalIdList(Lists.newArrayList(paypalIds));
		return offerInfo;
	}

	private Set<String> getPaypalIds(JSONObject itemJson) throws Exception
	{
		if(!itemJson.has("incentive_campaign_ids"))
			return Collections.emptySet();
		
		Set<String> paypalIds = Sets.newHashSet();
		
		JSONArray jsonIncentiveCampaignIds = itemJson.getJSONArray("incentive_campaign_ids");
		
		for(int i = 0 ;i < jsonIncentiveCampaignIds.length();i++)
			paypalIds.add(jsonIncentiveCampaignIds.getString(i));
		
		return paypalIds;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new OfferRetreivalServiceImpl().getAllOffersForZipCode("02110"));
	}

}
