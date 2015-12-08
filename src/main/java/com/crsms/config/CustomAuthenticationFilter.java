package com.crsms.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		return request.getParameter(getUsernameParameter().toLowerCase());
	}

}
