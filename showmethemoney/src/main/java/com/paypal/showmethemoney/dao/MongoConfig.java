package com.paypal.showmethemoney.dao;


import java.net.UnknownHostException;

import org.springframework.context.annotation.Configuration;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;

@Configuration
public class MongoConfig
{
	private static String host = "localhost";
	private static int port = 27017;
	private static String database = "hackathon";
	private static String zipcode_user_collection = "zip_user";
	private static String zipcode_offer_collection = "zip_offers";
	
	private static String qa_host = "lvs-atlas-qa1-mongodb3001.qa.paypal.com";
	private static String qa_database = "atlas";
	private static String qa_offer_collection = "offer";
	private static MongoClient qa_mongo_client;
	
	private static MongoClient client;
	
	static
	{
		String host = getHost();
		int port = getPort();

		try
		{
			client = new MongoClient(host, port);
			client.setReadPreference(ReadPreference.secondaryPreferred());
			client.setWriteConcern(WriteConcern.ACKNOWLEDGED);
			
			
			qa_mongo_client = new MongoClient(qa_host, port);
		}
		catch (UnknownHostException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static String getHost()
	{
		return host;
	}
	public static int getPort()
	{
		return port;
	}
	public static String getDatabase()
	{
		return database;
	}
	public static String getUserCollName()
	{
		return zipcode_user_collection;
	}
	public static String getOfferCollName()
	{
		return zipcode_offer_collection;
	}
	
	public static DBCollection getUserCollection() throws UnknownHostException
	{
		return client.getDB(getDatabase()).getCollection(getUserCollName());
	}
	
	public static DBCollection getOfferCollection() throws UnknownHostException
	{
		return client.getDB(getDatabase()).getCollection(getOfferCollName());
	}
	
	public static DBCollection getOfferInfoCollection() throws UnknownHostException
	{
		return qa_mongo_client.getDB(qa_database).getCollection(qa_offer_collection);
	}
}
