package com.crsms.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crsms.domain.Role;
import com.crsms.domain.Test;
import com.crsms.domain.User;
import com.crsms.service.RoleService;
import com.crsms.service.UserService;
/**
 * 
 * @author Roman Romaniuk
 *
 */
@Controller
@RequestMapping(value = { "/admin" })
public class AdminController {
	private static Logger log = LogManager.getLogger(AdminController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllUsers(ModelMap model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "admin";
	}

	@RequestMapping(value = { "/delete/{userId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable long userId) {
		userService.delete(userId);
		return "redirect:/admin";
	}

	@RequestMapping(value = { "/adduser" }, method = RequestMethod.GET)
	public String addUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "adduser";
	}

	@RequestMapping(value = { "/adduser" }, method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user, ModelMap model) {
		userService.saveUser(user);
		return "redirect:/admin";
	}

	@RequestMapping(value = { "/edit/{userId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable Long userId, ModelMap model) {
		User user = userService.getUserById(userId);
		model.addAttribute("user", user);
		return "adduser";
	}

	@RequestMapping(value = "/edit/{userId}", method = RequestMethod.POST)
	public String updateUser( @PathVariable long userId, @ModelAttribute("user") User user, @ModelAttribute("roles") Role role) {
		userService.updateUser(user);
		return "redirect:/admin";
	}
	
	@ModelAttribute("roles")
	public List<Role> initializeRoles() {
		List<Role> roles = new ArrayList<>();
		roles = roleService.getAllRoles();
		return roles;
	}

//	private String getPrincipal() {
//		String userEmail = null;
//		Object principal = SecurityContextHolder.getContext()
//				.getAuthentication().getPrincipal();
//
//		if (principal instanceof UserDetails) {
//			userEmail = ((UserDetails) principal).getUsername();
//		} else {
//			userEmail = principal.toString();
//		}
//		System.out.println(userEmail);
//		return userEmail;
//	}
}
