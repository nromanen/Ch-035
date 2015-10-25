package com.crsms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.crsms.domain.Role;
import com.crsms.domain.User;
import com.crsms.domain.UserInfo;
import com.crsms.service.UserInfoService;
import com.crsms.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping("/signUp")
	public String signUp(Model model) {
		return "signUp";
	};	
	
	@RequestMapping(value ="/submitUser", method = RequestMethod.POST)
	public String submitUser(Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
		Role role = new Role();
		role.setName("ROLE_STUDENT");
		role.setId((long) 2);
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);		
		userService.saveUser(user);
		model.addAttribute("email", email);
		model.addAttribute("password", password);
		model.addAttribute("id", user.getId());
		
		return "userProfile";
	};
	
	@RequestMapping(value ="/submitUserInfo", method = RequestMethod.POST)
	public String submitUserInfo(Model model, @RequestParam("fName") String fName, 
			@RequestParam("sName") String sName, @RequestParam("id") Long id) {
		User user = userService.getUserById(id);
		UserInfo userInf = new UserInfo();
		//userInf.setUserId(user.getId());
		userInf.setFirstName(fName);
		userInf.setSecondName(sName);
		userInf.setUser(user);
		userInfoService.saveUserInfo(userInf);
//		model.addAttribute("email", email);
//		model.addAttribute("password", password);
		return "userProfile";
	}	
	
/*
 	public String changePass(Model model, @RequestParam("id") Long id, @RequestParam("nPass") Long newPass) {
		User user = userService.getUserById(id);

		return "userProfile";
	}	
	*/
}
