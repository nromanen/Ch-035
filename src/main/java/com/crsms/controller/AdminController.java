package com.crsms.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crsms.domain.User;
import com.crsms.service.UserService;

@Controller
@RequestMapping(value = { "/admin" })
public class AdminController {
	private static Logger log = LogManager.getLogger(AdminController.class);

	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET)
	public String getAllUsers(ModelMap model) {
		List<User> users = service.getAllUsers();
		model.addAttribute("users", users);
		return "admin";
	}

	@RequestMapping(value = { "/delete/{userId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable long userId) {
		service.delete(userId);
		return "redirect:/admin";
	}

	@RequestMapping(value = { "/adduser/" }, method = RequestMethod.GET)
	public String addUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "adduser";
	}

	@RequestMapping(value = { "/adduser/" }, method = RequestMethod.POST)
	public String saveUser(User user, ModelMap model) {
		service.saveUser(user);
		return "redirect:/admin";
	}

	@RequestMapping(value = { "/edit/{userId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable Long userId, ModelMap model) {
		User user = service.getUserById(userId);
		model.addAttribute("user", user);
		return "edituser";
	}

	@RequestMapping(value = "/edit/{userId}", method = RequestMethod.POST)
	public String updateUser(@PathVariable Long roleId, @PathVariable long userId, User user) {
		service.updateUser(user);
		return "redirect:/admin";
	}

	private String getPrincipal() {
		String userEmail = null;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userEmail = ((UserDetails) principal).getUsername();
		} else {
			userEmail = principal.toString();
		}
		System.out.println(userEmail);
		return userEmail;
	}
}
