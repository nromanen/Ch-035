package com.crsms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Controller;
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
	private PersistentTokenRepository tokenRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage() {
		return "redirect:/courses/";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String loginPage(@RequestParam (
					value = "error", required = false) String error,  
					HttpServletRequest request, ModelMap model) {
		if (error != null) {
			model.addAttribute("error", selectErrorException(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
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
			model.addObject("username", userDetail.getUsername());
		}
		model.setViewName("403");
		return model;
	}
	
	private String selectErrorException(HttpServletRequest request, String key){
		Exception exception = 
                   (Exception) request.getSession().getAttribute(key);
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "credentialsException";
		} else if (exception instanceof LockedException) {
			error = "lockedException";
		} else if (exception instanceof DisabledException) {
			error = "disabledException";
		} else {
			error = request.getSession().getAttribute(key).toString();
		}
		return error;
	}
}