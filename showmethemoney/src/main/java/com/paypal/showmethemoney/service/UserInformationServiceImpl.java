package com.paypal.showmethemoney.service;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.paypal.showmethemoney.dao.MongoDaoImpl;
import com.paypal.showmethemoney.dao.UserInfo;

@Service
public class UserInformationServiceImpl implements UserInformationService
{
	MongoDaoImpl mongoDaoImpl;

	@Autowired
	public UserInformationServiceImpl(MongoDaoImpl mongoDaoImpl)
	{
		this.mongoDaoImpl = mongoDaoImpl;
	}

	public ImmutableList<UserInfo> getAllUsersInformation() throws UnknownHostException
	{
		return mongoDaoImpl.getAllUserInfo();
	}

}
