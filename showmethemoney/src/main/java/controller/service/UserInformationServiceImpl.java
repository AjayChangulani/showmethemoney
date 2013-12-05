package controller.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.ImmutableList;

import controller.dao.MongoDaoImpl;
import controller.dao.UserInfo;

public class UserInformationServiceImpl implements UserInformationService
{
	@Autowired
	MongoDaoImpl mongoDaoImpl;

	public ImmutableList<UserInfo> getAllUsersInformation()
	{
		return null;
	}

}
