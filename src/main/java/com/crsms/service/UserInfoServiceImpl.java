package com.crsms.service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crsms.domain.UserInfo;


@Service("userInfoService")
@Transactional( propagation = Propagation.REQUIRED)
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

	@Override
	public void update(UserInfo userInfo) {
		if (userInfo.getImage() == null
			|| !Pattern.compile("^data:image.+").matcher(userInfo.getImage()).matches()) {
			userInfo.setImage("");
		}
		
		super.update(userInfo);
	}
	
}
