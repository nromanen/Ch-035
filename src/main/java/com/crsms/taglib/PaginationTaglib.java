package com.crsms.taglib;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PaginationTaglib extends SimpleTagSupport {
	private static final String PARAM_PAGE = "page";
	
	private Long count;
	private Integer limit;
	
	private Writer getWriter() {
		return getJspContext().getOut();
	}
	
	@Override
	public void doTag() throws JspException {
		Writer out = getWriter();
		
		try {
			out.write("<nav>");
			out.write("<ul class='pagination'>");
			
			for (Long i = 0L; i < count; i += limit) {
				out.write(constructLink(i));
			}
						
			out.write("</ul>");
			out.write("</nav>");
		} catch (IOException e) {
			throw new JspException("Error in tag Pagination: ", e);
		}
	}
	
	private String constructLink(Long iteration) {
		Long page = iteration / limit + 1;
		StringBuilder link = new StringBuilder("<li");
		if (page.equals(getCurrentPageNumber())) {
			link.append(" class='active'");
		}
		link.append("><a href='?page=")
			.append(page)
			.append(getOtherRequestParameters())
			.append("'>")
			.append(page)
			.append("</a></li>");
		return link.toString();
	}

	private Long getCurrentPageNumber() {
		String currentPage = getServletRequest().getParameter(PARAM_PAGE);
		return currentPage != null ? Long.parseLong(currentPage) : 1L;
	}

	private ServletRequest getServletRequest() {
		return ((PageContext) getJspContext()).getRequest();
	}
	
	private String getOtherRequestParameters() {
		Map<String, String[]> parameters = getServletRequest().getParameterMap();
		StringBuilder requestParameters = new StringBuilder();
		for (Entry<String, String[]> param : parameters.entrySet()) {
			if (!param.getKey().equals(PARAM_PAGE)) {
				requestParameters.append("&")
								 .append(param.getKey())
								 .append("=")
								 .append(param.getValue()[0]);
			}
		}
		return requestParameters.toString();
	}
	
	public void setCount(Long count) {
		this.count = count;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
