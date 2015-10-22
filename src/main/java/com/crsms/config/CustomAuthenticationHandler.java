package com.crsms.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class CustomAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Bean
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		String teacherTargetUrl = "/teacher.jsp";
		String managerTargetUrl = "/manager.jsp";
		String studentTargetUrl = "/student.jsp";
		String adminTargetUrl = "/admin.jsp";
		Set<String> roles = AuthorityUtils
				.authorityListToSet(authentication.getAuthorities());
		if (roles.contains("ROLE_ADMIN")) {
			getRedirectStrategy().sendRedirect(request, response,
					adminTargetUrl);
		} else if (roles.contains("ROLE_TEACHER")) {
			getRedirectStrategy().sendRedirect(request, response,
					teacherTargetUrl);
		} else if (roles.contains("ROLE_MANAGER")) {
			getRedirectStrategy().sendRedirect(request, response,
					managerTargetUrl);
		} else if (roles.contains("ROLE_STUDENT")) {
			getRedirectStrategy().sendRedirect(request, response,
					studentTargetUrl);
		} else {
			super.onAuthenticationSuccess(request, response,
					authentication);
			return;
		}
	}
}
