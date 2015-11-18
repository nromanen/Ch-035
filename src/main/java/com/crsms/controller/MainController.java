package com.crsms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
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
	
	@Autowired
	PersistentTokenRepository tokenRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage() {
		return "redirect:/courses/";
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
	public String loginPage() {
		return "signin";
	}

	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, 
								HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
								.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			tokenRepository.removeUserTokens(auth.getName());
		}
		return "redirect:/signin?signout";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
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