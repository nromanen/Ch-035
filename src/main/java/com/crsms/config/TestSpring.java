package com.crsms.config;

//import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.crsms.domain.Role;
import com.crsms.domain.User;
import com.crsms.domain.UserInfo;
import com.crsms.service.UserService;

public class TestSpring {
public static void main (String [] args) {
	
	ApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
	UserService userService = context.getBean(UserService.class);
	Role newRole = new Role();
	newRole.setName("ROLE_TEACHER");
	
	UserInfo userinfo = new UserInfo();
	userinfo.setFirstName("firsname");
	userinfo.setLastName("lastname");
	
	
	User newUser = new User();
	newUser.setEmail("new@gmail.com");
	newUser.setPassword("newpassword");
	newUser.setRole(newRole);
	
//	
	userService.saveUser(newUser);
	newUser.setUserInfo(userinfo);
	userinfo.setUser(newUser);
	userService.saveUser(newUser);
	
	
}
}
