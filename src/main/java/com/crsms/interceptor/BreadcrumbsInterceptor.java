package com.crsms.interceptor;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.crsms.util.BreadcrumbsHelper;

public class BreadcrumbsInterceptor extends HandlerInterceptorAdapter {
	private static final String REGEXP_PARSE_URL = "([\\d]+\\/)?\\w+[\\/]?$"; //TODO name?
	private static final String REGEXP_REMOVE_NUMBERS = "\\d*\\/";
	private static final String REGEXP_REMOVE_SLASHES = "\\/";
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
							Object handler, ModelAndView modelAndView)
							throws Exception {		

		String url = request.getRequestURI();
		modelAndView.addObject("breadcrumbs", makeBreadcrumbs(url));
	}
	
	private Map<String, String> makeBreadcrumbs(String url) {
		String path = new String(url);
		String label = null;
		Map<String, String> breadcrumbs = new TreeMap<String, String>();
		
		Pattern pattern = Pattern.compile(REGEXP_PARSE_URL);
		Matcher matcher = pattern.matcher(path);
		
		String match = null;
		while (matcher.find()) {
			match = matcher.group();
			label = makeLabel(match);
			breadcrumbs.put(path, label);
			path = removeMatch(path, match);
			matcher = pattern.matcher(path);
		}
		
		return breadcrumbs;
	}

	private String makeLabel(String target) {
		String label = target.replaceAll(REGEXP_REMOVE_NUMBERS, "")
							 .replaceAll(REGEXP_REMOVE_SLASHES, "");
		return BreadcrumbsHelper.getCode(label);
	}	
	
	private String removeMatch(String path, String match) {
		return path.substring(0, path.lastIndexOf(match));
	}
	
}
