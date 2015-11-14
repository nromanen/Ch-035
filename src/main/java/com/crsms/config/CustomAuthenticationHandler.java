package com.crsms.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
/**
 * @author Roman Romaniuk
 */
@Component
public class CustomAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void handle(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			System.out.println("Can't redirect");
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {
		String url = "";
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}
		if (isAdmin(roles)) {
			url = "/admin/";
		} else if (isManager(roles)) {
			url = "/manager/";
		} else if (isTeacher(roles)) {
			url = "/teacher/";
		} else if (isStudent(roles)) {
			url = "/courses/?show=my";
		}else {
			url = "/403";
		}
		return url;
	}

	private boolean isAdmin(List<String> roles) {
		if (roles.contains("ROLE_ADMIN")) {
			return true;
		}
		return false;
	}

	private boolean isManager(List<String> roles) {
		if (roles.contains("ROLE_MANAGER")) {
			return true;
		}
		return false;
	}

	private boolean isTeacher(List<String> roles) {
		if (roles.contains("ROLE_TEACHER")) {
			return true;
		}
		return false;
	}

	private boolean isStudent(List<String> roles) {
		if (roles.contains("ROLE_STUDENT")) {
			return true;
		}
		return false;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
}
