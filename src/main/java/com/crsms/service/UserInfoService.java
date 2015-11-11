package com.crsms.service;

import com.crsms.domain.UserInfo;;

public interface UserInfoService {

	UserInfo saveUserInfo(UserInfo user);

	void delete(Long id);
	
}
