package com.crsms.interceptor;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.crsms.util.BreadcrumbsHelper;
import com.crsms.util.StringUtil;

public class BreadcrumbsInterceptor extends HandlerInterceptorAdapter {
	private static final Boolean LAST_WORD = true;
	private static final Integer MAX_NAME_LENGTH = 10;
	private static final String REGEXP_PARSE_URL = "\\w+[\\/]?$";
	private static final String REGEXP_IS_NUMBER = "\\d+";
	private static final String REGEXP_REMOVE_SLASHES = "\\/";
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {		
		String url = request.getRequestURI();
		Locale locale = LocaleContextHolder.getLocale();
		modelAndView.addObject("breadcrumbs", makeBreadcrumbs(url, locale));
	}
	
	private Map<String, String> makeBreadcrumbs(String url, Locale locale) {
		Map<String, String> breadcrumbs = splitUrl(url);
		
		setBreadcrumbsLabels(breadcrumbs, locale);
		
		//remove first entry (crsms)
		((TreeMap<String, String>) breadcrumbs).pollFirstEntry();
		return breadcrumbs;
	}

	private Map<String, String> splitUrl(String url) {
		String path = new String(url);
		Map<String, String> map = new TreeMap<>();
		
		Pattern pattern = Pattern.compile(REGEXP_PARSE_URL);
		Matcher matcher = pattern.matcher(path);
		
		String match = null;
		while (matcher.find()) {
			match = matcher.group();
			map.put(path, removeSlashes(match));
			path = removeMatch(path, match);
			matcher = pattern.matcher(path);
		}
		return map;
	}
	
	private void setBreadcrumbsLabels(Map<String, String> breadcrumbs, Locale locale) {
		String target = null;
		for (String key : breadcrumbs.keySet()) {
			String currentCrumb = breadcrumbs.get(key);
			
			if (!isNumber(currentCrumb)) {
				target = breadcrumbs.get(key);
				breadcrumbs.put(key, BreadcrumbsHelper.getLabel(currentCrumb, locale));
			} else {
				breadcrumbs.put(key, getTargetName(target, currentCrumb));
			}
		}
	}

	private String getTargetName(String target, String id) {
		return StringUtil.trimString(BreadcrumbsHelper.getName(target, id),
				MAX_NAME_LENGTH, LAST_WORD);
	}
	
	private boolean isNumber(String string) {
		return string.matches(REGEXP_IS_NUMBER);
	}
	
	private String removeSlashes(String target) {
		return target.replaceAll(REGEXP_REMOVE_SLASHES, "");
	}
	
	private String removeMatch(String path, String match) {
		return path.substring(0, path.lastIndexOf(match));
	}
	
}
