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
import org.springframework.web.bind.annotation.RequestParam;

import com.crsms.domain.User;
import com.crsms.service.UserService;
@Controller
public class AdminController {
	private static Logger log = LogManager.getLogger(AdminController.class);

	@Autowired
	private UserService service;

//	@RequestMapping(value = "/admin", method = RequestMethod.GET)
//	public String adminPage(ModelMap model) {
//		model.addAttribute("user", getPrincipal());
//		return "admin";
//	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAllUsers(ModelMap model) {
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }


	
//	@RequestMapping(value = "/by-{id}", method = RequestMethod.GET)
//
//	public User get(@PathVariable("id") long id) {
//		return service.getUserById(id);
//	}
//
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable long id) {
        service.delete(id);
        return "redirect:/admin";
    }

//	@RequestMapping(method = RequestMethod.PUT)
//	public void update(User user) {
//		service.updateUser(user);
//	}

//	@RequestMapping(value = "/by", method = RequestMethod.GET)
//	public User getByMail(@RequestParam("email") String email) {
//		return service.getUserByEmail(email);
//	}

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
