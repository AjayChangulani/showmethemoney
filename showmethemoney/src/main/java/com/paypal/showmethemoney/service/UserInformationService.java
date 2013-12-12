package com.paypal.showmethemoney.service;

import java.net.UnknownHostException;

import com.google.common.collect.ImmutableList;
import com.paypal.showmethemoney.dao.UserInfo;


public interface UserInformationService
{
	ImmutableList<UserInfo> getAllUsersInformation() throws UnknownHostException;
}
