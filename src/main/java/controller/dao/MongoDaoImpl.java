package controller.dao;

import java.net.UnknownHostException;

import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;


public class MongoDaoImpl implements MongoDao
{
	
	ObjectMapper objMapper = new ObjectMapper();
		
	public void saveStore(StoreInfo storeInfo) throws UnknownHostException
	{
		
		BasicDBObject saveStoreDBObject = objMapper.convertValue(storeInfo, BasicDBObject.class);
		
		MongoConfig.getCollection().save(saveStoreDBObject);
	}

	public StoreInfo getStore()
	{
		return null;
	}

}
