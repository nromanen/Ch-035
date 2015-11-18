<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h3><spring:message code = "crsms.text.error"/></h3>
<p>${exception.message}</p>

<% ((Exception) request.getAttribute("javax.servlet.error.exception")).printStackTrace(new java.io.PrintWriter(out)); %>