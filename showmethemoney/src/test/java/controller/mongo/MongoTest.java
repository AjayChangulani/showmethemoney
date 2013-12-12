package controller.mongo;

import java.net.UnknownHostException;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.paypal.showmethemoney.dao.MongoConfig;

public class MongoTest
{
	private static String host = "lvs-atlas-qa1-mongodb3001.qa.paypal.com";
	private static int port = 27017;
	private static String database = "atlas";
	private static String collection = "offer";
	private static MongoClient client;
	
	@Test
	public void test()
	{

		try
		{
			client = new MongoClient(host, port);

			DBCursor storeResult = client.getDB(database).getCollection(collection).find();
			
			int i=10;
			while(i>1)
			{
				--i;
				System.out.println(storeResult.next());
			}
			//			client.setReadPreference(ReadPreference.secondaryPreferred());
//			client.setWriteConcern(WriteConcern.ACKNOWLEDGED);
		}
		catch (UnknownHostException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test2()
	{
		BasicDBObject projections = new BasicDBObject();
		projections.put("_id", 0);
		projections.put("offer_id", 1);
		
		DBObject findQuery = QueryBuilder.start("incentive_campaign_id").is("92a711e0-4507-0131-435a-12313d2f499c").get();
		DBCursor resultSet = client.getDB(database).getCollection(collection).find(findQuery, projections);
		
		String offerId = null;
		if(resultSet != null)
		{
			offerId = resultSet.next().get("offer_id").toString();
		}
		
		System.out.println(offerId);
	}
}
