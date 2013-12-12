package com.paypal.showmethemoney.service;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.ImmutableList;
import com.paypal.showmethemoney.dao.MongoDaoImpl;
import com.paypal.showmethemoney.dao.UserInfo;


public class UserInformationServiceImpl implements UserInformationService
{
	@Autowired
	MongoDaoImpl mongoDaoImpl;

	public ImmutableList<UserInfo> getAllUsersInformation() throws UnknownHostException
	{
		return mongoDaoImpl.getAllUserInfo();
	}

}
