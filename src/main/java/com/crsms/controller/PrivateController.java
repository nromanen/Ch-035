package com.crsms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/private")
public class PrivateController {
	@RequestMapping("/")
	public String redirect(HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/private/admin/";
		} else if (request.isUserInRole("TEACHER")) {
			return "redirect:/private/courses/";
		}
		return "403";
	}
}

