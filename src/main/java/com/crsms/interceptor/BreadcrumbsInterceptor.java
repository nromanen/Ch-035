package com.crsms.interceptor;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.crsms.util.BreadcrumbsHelper;

public class BreadcrumbsInterceptor extends HandlerInterceptorAdapter {
	
	private BreadcrumbsHelper breadcrumbsHelper = new BreadcrumbsHelper();
	
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
		
		List<String> pathes = new LinkedList<String>();
		List<String> labels = new LinkedList<String>();
		
		String regex = "([\\d]+\\/)?\\w+[\\/]?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(path);
		
		String match = null;
		while (matcher.find()) {
			match = matcher.group();
			label = makeLabel(match);
			labels.add(label);
			pathes.add(path);
			path = path.substring(0, path.lastIndexOf(match));
			matcher = pattern.matcher(path);
		}
		
		return makeMap(pathes, labels);
	}

	private Map<String, String> makeMap(List<String> pathes, List<String> labels) {
		Map<String, String> result = new LinkedHashMap<String, String>();
		
		Collections.reverse(pathes);
		Collections.reverse(labels);
		
		Iterator<String> pathesIterator = pathes.iterator();
		Iterator<String> labelsIterator = labels.iterator();
		
		while (pathesIterator.hasNext() && labelsIterator.hasNext()) {
			result.put(pathesIterator.next(), labelsIterator.next());
		}
		
		return result;
	}
	
	private String makeLabel(String target) {
		String label = target.replaceAll("\\d*\\/", "");
		label = label.replaceAll("\\/", "");
		return breadcrumbsHelper.getCode(label);
	}
	
}
