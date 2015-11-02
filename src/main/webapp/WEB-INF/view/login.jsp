<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>

<h3>
	<spring:message code="crsms.login.text.desc" />
</h3>

<c:if test="${not empty error}">
	<div class="error hide">${error}</div>
</c:if>
<c:if test="${not empty msg}">
	<div class="msg hide">${msg}</div>
</c:if>


<form name='loginForm' action="<c:url value='/login' />" method='POST'>
	<div class="form-group">
		<label for="email"><spring:message code="crsms.text.email" /></label>
		<input type="email" class="form-control" id="email" name="email"
			placeholder="Email">
	</div>
	<div class="form-group">

		<label for="password"><spring:message
				code="crsms.text.password" /></label> <input type="password"
			class="form-control" id="password" name="password"
			placeholder="Password">
	</div>
	<div class="input-group input-sm">
		<label for="rememberme"><spring:message
				code="crsms.text.rememberme" /></label>
		<div class="checkbox">
			<label><input type="checkbox" id="rememberme"
				name="remember-me" placeholder="Rememberme"></label>
		</div>
	</div>

	<button type="submit" class="btn btn-default">
		<spring:message code="crsms.button.log.in" />
	</button>

	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />

</form>