package com.paypal.showmethemoney.dao;

import java.net.UnknownHostException;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

@Repository
public class MongoDaoImpl implements MongoDao
{
	
	private ObjectMapper objMapper;
	private static final String ZIPCODE_FIELD_NAME = "zipcode";

	public MongoDaoImpl()
	{
		objMapper = new ObjectMapper();
	}
	
	public void saveUserInfo(UserInfo userInfo) throws UnknownHostException
	{
		DBObject upsertQuery = QueryBuilder.start(ZIPCODE_FIELD_NAME).is(userInfo.getZipcode()).get();
		
		BasicDBObject saveUserInfoObject = objMapper.convertValue(userInfo, BasicDBObject.class);
		
		MongoConfig.getUserCollection().update(upsertQuery, saveUserInfoObject, true ,false);
	}

	public void saveOfferInfo(OfferInfo offerInfo) throws UnknownHostException
	{
		DBObject upsertQuery = QueryBuilder.start(ZIPCODE_FIELD_NAME).is(offerInfo.getZipcode()).get();
	
		BasicDBObject saveOfferInfoObject = objMapper.convertValue(offerInfo, BasicDBObject.class);
		
		MongoConfig.getOfferCollection().update(upsertQuery, saveOfferInfoObject, true ,false);
	}

	public Optional<UserInfo> getUserInfo(String zip) throws UnknownHostException
	{
		DBObject findQuery = QueryBuilder.start(ZIPCODE_FIELD_NAME).is(zip).get();
		DBCursor resultSet = MongoConfig.getUserCollection().find(findQuery);
		
		Optional<UserInfo> userInfo = Optional.absent();
		
		if(resultSet.hasNext())
		{
			userInfo = Optional.of(objMapper.convertValue(resultSet.next(), UserInfo.class));
		}

		return userInfo;
	}

	public Optional<OfferInfo> getOfferInfo(String zip) throws UnknownHostException
	{
		DBObject findQuery = QueryBuilder.start(ZIPCODE_FIELD_NAME).is(zip).get();
		DBCursor resultSet = MongoConfig.getOfferCollection().find(findQuery);
		
		Optional<OfferInfo> offerInfo = Optional.absent();
		
		if(resultSet.hasNext())
		{
			offerInfo = Optional.of(objMapper.convertValue(resultSet.next(), OfferInfo.class));
		}

		return offerInfo;
	}
		
	public Optional<ImmutableList<String>> getAllEmailAddresses(String zip) throws UnknownHostException
	{
		DBObject findQuery = QueryBuilder.start(ZIPCODE_FIELD_NAME).is(zip).get();
		DBCursor resultSet = MongoConfig.getUserCollection().find(findQuery);
		
		ImmutableList.Builder<String> listBuilder = ImmutableList.<String> builder();
		Optional<ImmutableList<String>> list = Optional.absent();
		
		UserInfo userInfo = new UserInfo();
		
		if(resultSet.hasNext())
		{
			userInfo = objMapper.convertValue(resultSet.next(), UserInfo.class);
			listBuilder.addAll(userInfo.getEmailList());
			list = Optional.of(listBuilder.build());
		}

		return list;
	}

	public ImmutableList<UserInfo> getAllUserInfo() throws UnknownHostException
	{
		DBCursor resultSet = MongoConfig.getUserCollection().find();
		ImmutableList.Builder<UserInfo> userInfoListBuilder = ImmutableList.<UserInfo> builder();
		System.out.println("before while in mongo");
		while(resultSet.hasNext())
		{
			userInfoListBuilder.add(objMapper.convertValue(resultSet.next(), UserInfo.class));
			System.out.println("inside mongodao - getAllUserInfo");
		}
		System.out.println("after while in mongo");
		return userInfoListBuilder.build();
	}

	public String findOfferIdBasedOnPayPalId(String paypalId) throws UnknownHostException
	{
		BasicDBObject projections = new BasicDBObject();
		projections.put("_id", 0);
		projections.put("offer_id", 1);
		
		DBObject findQuery = QueryBuilder.start("incentive_campaign_id").is(paypalId).get();
		DBCursor resultSet = MongoConfig.getOfferInfoCollection().find(findQuery, projections);
		
		String offerId = null;
		
		if(resultSet.hasNext())
		{
			offerId = resultSet.next().get("offer_id").toString();
		}
		
		return offerId;
	}

}
