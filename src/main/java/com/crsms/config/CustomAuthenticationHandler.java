
package com.crsms.config;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private final Logger logger = LogManager.getLogger(CustomAuthenticationHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void handle(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			logger.error("Error redirect");
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {
		String url = "";
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Map<String, String> roleToUrlMapper = new HashMap<String, String>();
		roleToUrlMapper.put("ROLE_ADMIN", "/admin/");
		roleToUrlMapper.put("ROLE_MANAGER", "/areas/");
		roleToUrlMapper.put("ROLE_TEACHER", "/courses/?show=my");
		roleToUrlMapper.put("ROLE_STUDENT", "/courses/?show=my");

		outer: for (GrantedAuthority auth : authorities) {
			for (Map.Entry<String, String> entry : roleToUrlMapper.entrySet()) {
				if (auth.getAuthority().equals(entry.getKey())) {
					url = entry.getValue();
					break outer;
				} else {
					url = "/403";
				}
			}
		}
		return url;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
}
