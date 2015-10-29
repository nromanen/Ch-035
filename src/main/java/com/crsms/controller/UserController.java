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

import com.crsms.service.RoleService;
import com.crsms.service.UserInfoService;
import com.crsms.service.UserService;

@Controller
public class UserController {
	
	private static final long STUDENT_ROLE_ID = 2;

	@Autowired
	private UserService userService;

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private RoleService roleService;

	@RequestMapping("/signUp")
	public String signUp(Model model) {
		return "signUp";
	};

	@RequestMapping(value = "/submitUser", method = RequestMethod.POST)
	public String submitUser(Model model, HttpSession session,
			@RequestParam("email") String email, @RequestParam("password") String password) {
		userService.createUser(email, password, STUDENT_ROLE_ID);
		session.setAttribute("email", email);
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
		userInfoService.createUserInfo(fName, sName, email);
		return "redirect:/signUp"; //"logout"
	}
	
	@ResponseBody
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(HttpSession session, 
			@RequestParam("currentPass") String currentPassword, @RequestParam("newPassword") String newPassword) {
		String email = (String) session.getAttribute("email");
		return userService.changePassword(email, currentPassword, newPassword) ? "Success" : "Fail";
	}

}
