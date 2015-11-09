package com.crsms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * @author Roman Romaniuk
 *
 */
@Controller
public class MainController {
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String homePage(){
		return "index";
	}
	

	@RequestMapping(value = {"/hello" }, method = RequestMethod.GET)
	public String helloPage(ModelMap model) {
		model.addAttribute("title", "Course Management System");
		model.addAttribute("message", "This is default page for all users!");
		return ("hello");
	}

	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String managerPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "manager";
	}

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public String studentPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "student";
	}

	@RequestMapping(value = "/teacher", method = RequestMethod.GET)
	public String teacherPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "teacher";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String loginPage(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "signout", required = false) String signout,
			HttpServletRequest request, ModelMap model) {
		if (error != null) {
			model.addAttribute("error",
					getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
		if (signout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}
		return "signin";
	}

	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession()
				.getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username or password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "account is locked!";
		}
		return error;
	}

	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/signin?signout";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);
			model.addObject("username", userDetail.getUsername());
		}
		model.setViewName("403");
		return model;
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}