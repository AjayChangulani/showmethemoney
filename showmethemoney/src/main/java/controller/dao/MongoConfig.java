package controller.dao;


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
}
