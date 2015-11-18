package com.crsms.dao;

import org.springframework.stereotype.Repository;

import com.crsms.domain.UserInfo;

@Repository("userInfoDao")
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo> implements UserInfoDao {

}
