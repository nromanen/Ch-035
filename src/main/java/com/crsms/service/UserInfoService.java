package com.crsms.service;

import com.crsms.domain.UserInfo;;

public interface UserInfoService {
	
	UserInfo createUserInfo(String fName, String sName, String email);

	UserInfo saveUserInfo(UserInfo user);

	void delete(Long id);
	
}
