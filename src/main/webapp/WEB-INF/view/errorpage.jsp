<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h3><spring:message code = "crsms.text.error"/></h3>
<h4 class="">${exception.message}</h4>

<a class="btn btn-default text-capitalize" role="button" data-toggle="collapse" href="#stack-trace" 
	aria-expanded="false" aria-controls="stack-tracee"
	><spring:message code = "crsms.errorpage.btn.showstacktrace"/></a>
<div class="collapse" id="stack-trace">
  <div class="well">
    <span><% ((Exception) request.getAttribute("javax.servlet.error.exception")).printStackTrace(new java.io.PrintWriter(out)); %></span>
  </div>
</div>
