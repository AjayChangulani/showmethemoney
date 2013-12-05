package controller.service;

import com.google.common.collect.ImmutableList;

import controller.dao.UserInfo;

public interface UserInformationService
{
	ImmutableList<UserInfo> getAllUsersInformation();
}
