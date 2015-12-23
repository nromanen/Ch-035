package com.crsms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HttpErrorController {
	public static final String DEFAULT_ERROR_PAGE = "errorpage";
	public static final String PAGE_NOT_FOUND = "404";
	public static final String ACCESS_DENIED = "403";
	
	@RequestMapping("/error")
	public String error() {
		return DEFAULT_ERROR_PAGE;
	}
	
	@RequestMapping("/403")
	public String error403() {
		return ACCESS_DENIED;
	}
	
	@RequestMapping("/404")
	public String error404() {
		return PAGE_NOT_FOUND;
	}

}
