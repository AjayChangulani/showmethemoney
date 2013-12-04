package controller.dao;

import java.net.UnknownHostException;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public interface MongoDao
{
	void saveUserInfo(UserInfo userInfo) throws UnknownHostException;
	void saveOfferInfo(OfferInfo offerInfo) throws UnknownHostException;
	
	Optional<UserInfo> getUserInfo(String zip) throws UnknownHostException;
	Optional<OfferInfo> getOfferInfo(String zip) throws UnknownHostException;
	
	Optional<ImmutableList<String>> getAllEmailAddresses(String zip) throws UnknownHostException;
}
