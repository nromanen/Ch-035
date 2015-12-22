package com.crsms.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import javax.mail.MessagingException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crsms.domain.User;
import com.crsms.domain.UserInfo;
import com.crsms.service.EncryptService;
import com.crsms.service.UserInfoService;
import com.crsms.service.UserService;
import com.crsms.service.MailService;
import com.crsms.validator.UserInfoValidator;
import com.crsms.validator.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private UserInfoValidator userInfoValidator;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private EncryptService encryptService;

	
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
	
	@RequestMapping(value = "/inactiveProfile", method = RequestMethod.GET)
	public String inactiveProfile(Model model) {
		return "inactiveProfile";
	}

	@RequestMapping(value = "/submitUser", method = RequestMethod.POST)
	public String submitUser(@Validated @ModelAttribute("userRegistr") User user,
			BindingResult result, Principal principal, Model model,	@RequestParam(value = "teacher",
			required = false, defaultValue = "false") boolean teacher)
			throws MessagingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {
		if (result.hasErrors()) {
			return "signUp";
		}

		userService.saveUser(user, teacher);
		mailService.sendConfirmation(user.getEmail(), user.getId());
		
		model.addAttribute(user);
		
		String currentUserEmail = (principal == null)? null: principal.getName();
		
		if(currentUserEmail!=null&&(userService.getUserByEmail(currentUserEmail).getIsEnabled())){
			return "inactiveProfile";
		}
		
		return "redirect:/inactiveProfile"; 
	}
		
	@RequestMapping(value = "/user/{encripted}/activated", method = RequestMethod.GET)
	public String activateUser(Model model,  @PathVariable("encripted") String encripted) 
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, 
			IllegalBlockSizeException, BadPaddingException {
		long id = encryptService.decrypt(encripted);
		User user = userService.getById(id);		
		userService.activateUser(user);

		return "redirect:/signin";
	}
	
	@RequestMapping(value = "/userProfile")
	public String createdUserProfile(Model model) {
		String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("userInfo", userService.getUserByEmail(currentUserEmail).getUserInfo());

		return "userProfile";
	}

	@RequestMapping(value = "/submitUserInfo", method = RequestMethod.POST)
	public String submitUserInfo(@Validated @ModelAttribute("userInfo") UserInfo newUserInfo,
									BindingResult result) {
		if (result.hasErrors()) {
			return "userProfile";
		}
		String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		newUserInfo.setUser(userService.getUserByEmail(currentUserEmail));
		userInfoService.update(newUserInfo);

		return "redirect:/courses/";
	}
		
	@ResponseBody
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(HttpSession session, 
								@RequestParam("currentPass") String currentPassword,
								@RequestParam("newPassword") String newPassword) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userService.changePassword(email, currentPassword, newPassword) ? "Success" : "Fail";
	}

}
