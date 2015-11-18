package com.crsms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crsms.domain.User;
import com.crsms.domain.UserInfo;
import com.crsms.service.RoleService;
import com.crsms.service.UserInfoService;
import com.crsms.service.UserService;
import com.crsms.validator.UserInfoValidator;
import com.crsms.validator.UserValidator;

@Controller
public class UserController {
	
	private static final long STUDENT_ROLE_ID = 2;

	@Autowired
	private UserService userService;

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private UserInfoValidator userInfoValidator;
	
	@InitBinder("userRegistr")
    private void initUserBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
    }

	@InitBinder("userInfo")
    private void initUserInfoBinder(WebDataBinder binder) {
		binder.setValidator(userInfoValidator);
    }
	
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String signUp(Model model) {
		model.addAttribute("userRegistr", new User());
		return "signUp";
	};
	
	@RequestMapping(value = "/submitUser", method = RequestMethod.POST)
	public String submitUser(@Validated @ModelAttribute("userRegistr")  User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "signUp";
		}
	
		userService.saveUser(user);	

		user.setRole(roleService.getRoleById(STUDENT_ROLE_ID));
		userService.update(user);
		UserInfo userInfo = new UserInfo();
		userInfoService.save(userInfo);
		userInfo.setUser(user);
		userInfoService.update(userInfo);
		
		model.addAttribute(user);
		
		return "redirect:/signin"; 
	};
	
	@RequestMapping(value = "/userProfile")
	public String createdUserProfile(Model model) {
		model.addAttribute("userInfo", userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getUserInfo());
		
		return "userProfile";
	}

	@RequestMapping(value = "/submitUserInfo", method = RequestMethod.POST)
	public String submitUserInfo(@Validated @ModelAttribute("userInfo") UserInfo newUserInfo,  BindingResult result) {
		if (result.hasErrors()) {
			return "userProfile";
		}
		newUserInfo.setUser(userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
		userInfoService.update(newUserInfo);

		return "redirect:/courses/?show=my";
	}
	
	@ResponseBody
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(HttpSession session, 
			@RequestParam("currentPass") String currentPassword, @RequestParam("newPassword") String newPassword) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userService.changePassword(email, currentPassword, newPassword) ? "Success" : "Fail";
	}

}
