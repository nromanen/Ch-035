package com.crsms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HolidayInterceptor extends HandlerInterceptorAdapter {
	
	private static final int X_MAS_START_MONTH = 12;
	private static final int X_MAS_START_DAY = 19;
	private static final int X_MAS_DURATION_DAYS = 33;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		DateTime currentDate = new DateTime();
		DateTime xMasStartDate = new DateTime(currentDate)
				.plusMonths(X_MAS_START_MONTH - currentDate.getMonthOfYear())
				.plusDays(X_MAS_START_DAY - currentDate.getDayOfMonth());
		DateTime xMasEndDate = new DateTime(xMasStartDate).plusDays(X_MAS_DURATION_DAYS);
		if (currentDate.isAfter(xMasStartDate) && currentDate.isBefore(xMasEndDate)) {
			modelAndView.addObject("isXmas", true);
		}
	}
	
}
