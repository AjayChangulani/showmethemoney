package controller.dao;

import java.net.UnknownHostException;

public interface MongoDao
{
	void saveStore(StoreInfo storeInfo) throws UnknownHostException;
	StoreInfo getStore();
}
