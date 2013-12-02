package controller.dao;


import java.net.UnknownHostException;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;

public class MongoConfig
{
	private static String host = "localhost";
	private static int port = 27017;
	private static String database = "hackit";
	private static String collection = "storeinfo";
	
	
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
	public static String getColl()
	{
		return collection;
	}
	
	public static MongoClient getMongoClient() throws UnknownHostException
	{
		MongoClient client;

		String host = getHost();
		int port = getPort();

		client = new MongoClient(host, port);

		client.setReadPreference(ReadPreference.secondaryPreferred());
		client.setWriteConcern(WriteConcern.ACKNOWLEDGED);

		return client;
	}
	public static DBCollection getCollection() throws UnknownHostException
	{
		return getMongoClient().getDB(getDatabase()).getCollection(getColl());
	}
}
