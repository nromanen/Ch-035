package com.crsms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.dao.UserInfoDao;
import com.crsms.domain.UserInfo;


@Service("userInfoService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)

public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Override
	@Transactional
	public UserInfo saveUserInfo(UserInfo user) {
		return userInfoDao.saveUserInfo(user);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		userInfoDao.delete(id);
	}

}
