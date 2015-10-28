package com.crsms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(value = "/submitUser", method = RequestMethod.POST)
	public String submitUser(Model model, HttpSession session,
			@RequestParam("email") String email, @RequestParam("password") String password) {
		Role role = new Role();
		role.setName("ROLE_STUDENT");
		role.setId((long) 2);

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);
		userService.saveUser(user);
		session.setAttribute("email", email); // add to servise

		return "redirect:/userProfile"; ///"login";
	};
	
	@RequestMapping("/userProfile")
	public String userProfile() {
		return "userProfile";
	}

	@RequestMapping(value = "/submitUserInfo", method = RequestMethod.POST)
	public String submitUserInfo(Model model,
			@RequestParam("fName") String fName,
			@RequestParam("sName") String sName,
			@ModelAttribute("email") String email) {
		User user = userService.getUserByEmail(email);

		UserInfo userInf = new UserInfo();
		userInf.setFirstName(fName);
		userInf.setSecondName(sName);
		userInf.setUser(user);
		userInfoService.saveUserInfo(userInf);
		return "redirect:/signUp"; //"logout"
	}
	
	@ResponseBody
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(HttpSession session, 
			@RequestParam("currentPass") String currentPassword, @RequestParam("newPassword") String newPassword) {
		String email = (String) session.getAttribute("email");
		User user = userService.getUserByEmail(email);
		
		if (!user.getPassword().equals(currentPassword)) {
			return "Fail";
		}
		
		user.setPassword(newPassword);
		userService.saveUser(user);
		return "Success";
	}

}
