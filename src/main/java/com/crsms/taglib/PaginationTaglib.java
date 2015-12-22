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
	private static final Long DEFAULT_MAX_PAGES_COUNT = 5L;
	private static final Long FIRST_PAGE_NUMBER = 1L;
	
	private Long count;
	private Integer limit;
	private Long maxPagesCount = DEFAULT_MAX_PAGES_COUNT;
	private String firstPageLabel = "&laquo;";
	private String lastPageLabel = "&raquo;";
	
	private Writer getWriter() {
		return getJspContext().getOut();
	}
	
	@Override
	public void doTag() throws JspException {
		if (count <= limit) {
			return;
		}
		
		Writer out = getWriter();
		
		Long firstPageNumber = getFirstPageNumber();
		Long lastPageNumber = getLastPageNumber();
		
		try {
			out.write("<nav>");
			out.write("<ul class='pagination'>");
			out.write(constructGoToPageLink(firstPageNumber, firstPageLabel));
			out.write(constructPages(firstPageNumber, lastPageNumber));
			out.write(constructGoToPageLink(lastPageNumber, lastPageLabel));
			out.write("</ul>");
			out.write("</nav>");
		} catch (IOException e) {
			throw new JspException("Error in tag Pagination: ", e);
		}
	}

	private long getFirstPageNumber() {
		return FIRST_PAGE_NUMBER;
	}

	private long getLastPageNumber() {
		return new Double(Math.ceil(new Double(count) / limit)).longValue();
	}
	
	private String constructPages(Long firstPageNumber, Long lastPageNumber) {
		Long currentPageNumber = getCurrentPageNumber();
		StringBuilder pages = new StringBuilder();
		
		if (lastPageNumber <= maxPagesCount) {
			for (Long page = firstPageNumber; page <= lastPageNumber; page++) {
				pages.append(constructLink(page));
			}
			return pages.toString();
		}
		
		Long beginPage;
		Long endPage;
		
		if (currentPageNumber <= maxPagesCount / 2) {
			beginPage = firstPageNumber;
			endPage = beginPage + maxPagesCount - 1;
		} else if (lastPageNumber - currentPageNumber <= maxPagesCount / 2) {
			beginPage = lastPageNumber - maxPagesCount + 1;
			endPage = lastPageNumber;
		} else {
			beginPage = currentPageNumber - ((maxPagesCount - 1) / 2);
			endPage = currentPageNumber + ((maxPagesCount - 1) / 2);
		}
		
		if (beginPage != firstPageNumber) {
			pages.append(constructDots());
		}
		
		for (Long page = beginPage; page <= endPage; page++) {
			pages.append(constructLink(page));
		}
		
		if (endPage != lastPageNumber) {
			pages.append(constructDots());
		}
		
		return pages.toString();
	}
	
	private String constructDots() {
		return "<li><a>...</a></li>";
	}
	
	private String constructGoToPageLink(Long pageNumber, String label) {
		StringBuilder link = new StringBuilder("<li");
		if (getCurrentPageNumber() == null || getCurrentPageNumber() == pageNumber) {
			link.append(" class='disabled'><a>");
		} else {
			link.append("><a href='?page=")
			.append(pageNumber)
			.append(getOtherRequestParameters())
			.append("'>");
		}
		link.append(label)
			.append("</a></li>");
		return link.toString();
	}
	
	private String constructLink(Long page) {
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
		return currentPage == null ? FIRST_PAGE_NUMBER : Long.parseLong(currentPage);
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

	public void setMaxPagesCount(Long maxPagesCount) {
		this.maxPagesCount = maxPagesCount;
	}

	public void setFirstPageLabel(String firstPageLabel) {
		this.firstPageLabel = firstPageLabel;
	}

	public void setLastPageLabel(String lastPageLabel) {
		this.lastPageLabel = lastPageLabel;
	}
	
}
