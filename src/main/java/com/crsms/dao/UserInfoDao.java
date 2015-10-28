package com.crsms.dao;

import com.crsms.domain.UserInfo;

public interface UserInfoDao {

	UserInfo saveUserInfo(UserInfo user);

	void delete(Long id);

}
