package com.paypal.showmethemoney.service;

import com.google.common.collect.ImmutableList;
import com.paypal.showmethemoney.dao.UserInfo;


public interface UserInformationService
{
	ImmutableList<UserInfo> getAllUsersInformation();
}
