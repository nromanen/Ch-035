package com.crsms.tag;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SortingTag extends SimpleTagSupport {
	private static final String PARAM_SORT_BY = "sortBy";
	private static final String PARAM_ORDER = "order";
	private static final String ORDER_ASC = "asc";
	private static final String ORDER_DESC = "desc";
	
	private String text;
	private String sortBy;
	
	private Writer getWriter() {
		return getJspContext().getOut();
	}
	
	@Override
	public void doTag() throws JspException {
		Writer out = getWriter();
		
		try {
			out.write(text + " ");
			out.write(constructLink());
		} catch (IOException e) {
			throw new JspException("Error in tag Sort: ", e);
		}
	}
	
	private String constructLink() {
		StringBuilder link = new StringBuilder();
		link.append("<a href='?sortBy=")
			.append(sortBy)
			.append(order())
			.append(getOtherRequestParameters())
			.append("'>")
			.append(icon())
			.append("</a>");
		return link.toString();
	}
	
	private String order() {
		StringBuilder order = new StringBuilder("&order=");
		String requestOrder = getServletRequest().getParameter(PARAM_ORDER);
		if (requestOrder != null && requestOrder.equals(ORDER_DESC)) {
			order.append(ORDER_ASC);
		} else {
			order.append(ORDER_DESC);
		}
		return order.toString();
	}

	private String icon() {
		ServletRequest request = getServletRequest();

		String requestSortBy = request.getParameter(PARAM_SORT_BY);
		String requestOrder = request.getParameter(PARAM_ORDER);
		
		StringBuilder icon = new StringBuilder("<i class='fa");
		
		if (requestSortBy == null || !requestSortBy.equals(sortBy)) {
			icon.append(" fa-sort");
		} else if (requestOrder != null && requestOrder.equals(ORDER_DESC)) {
			icon.append(" fa-sort-alpha-desc");
		} else {
			icon.append(" fa-sort-alpha-asc");
		}
		
		icon.append(" fa-lg'></i>");
		return icon.toString();
	}
	
	private String getOtherRequestParameters() {
		Map<String, String[]> parameters = getServletRequest().getParameterMap();
		StringBuilder requestParameters = new StringBuilder();
		for (Entry<String, String[]> param : parameters.entrySet()) {
			if (!param.getKey().equals(PARAM_SORT_BY) && !param.getKey().equals(PARAM_ORDER)) {
				requestParameters.append("&")
								 .append(param.getKey())
								 .append("=")
								 .append(param.getValue()[0]);
			}
		}
		return requestParameters.toString();
	}

	private ServletRequest getServletRequest() {
		return ((PageContext) getJspContext()).getRequest();
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	
}
