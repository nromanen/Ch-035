package com.crsms.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.crsms.domain.User;
import com.crsms.service.UserService;

public class TestSpring {
public static void main (String [] args) {
	
//	ApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
//	UserService userService = context.getBean(UserService.class);
	
	User newUser = new User();
	newUser.setEmail("new@gmail.com");
	newUser.setPassword("newpassword");
//	userService.saveUser(newUser);
	
	
	
}
}
