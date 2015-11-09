<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>


<div class="container">
	<div class="row">
		<div class="col-sm-6 col-md-4 col-md-offset-4">
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong class=""><spring:message code="crsms.signin.formheader"/></strong>

				</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form"
						action="<c:url value="/signin" />" method="POST">
						<div class="form-group">
							<label for="email" class="col-sm-3 control-label"> <spring:message
									code="crsms.text.email" />
							</label>
							<div class="col-sm-9">
								<input type="email" class="form-control" id="email" name="email"
									placeholder="Email" required autofocus>
								<form:errors path="email" cssClass="label label-danger" />
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label"><spring:message
									code="crsms.text.password" /></label>
							<div class="col-sm-9">
								<input type="password" class="form-control" id="password"
									name="password" placeholder="Password">
								<form:errors path="password" cssClass="label label-danger" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-9">
								<div class="checkbox">
									<label for="rememberme"> <input type="checkbox"
										id="rememberme" name="remember-me" placeholder="Rememberme">
										<spring:message code="crsms.signin.rememberme" />
									</label>
								</div>
							</div>
						</div>
						<div class="form-group last">
							<div class="col-sm-offset-3 col-sm-9">
								<button type="submit" class="btn btn-success btn-sm">
									<spring:message code="crsms.button.signin" />
								</button>
								<button type="reset" class="btn btn-default btn-sm">
									<spring:message code="crsms.button.reset" />
								</button>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</div>
						</div>
					</form>
				</div>
				<div class="panel-footer">
					<spring:message code="crsms.signin.notregistered" />
					<a href="${pageContext.request.contextPath}/signUp" class=""> <spring:message
							code="crsms.signin.register" />
					</a>
				</div>
			</div>
		</div>
	</div>
</div>




<%-- 

<form name='loginForm' action="<c:url value='/login' />" method='POST'>
	<div class="form-group">
		<label for="email"><spring:message code="crsms.text.email" /></label>
		<input type="email" class="form-control" id="email" name="email"
			placeholder="Email">
	</div>
	<div class="form-group">
		<label for="password"><spring:message	code="crsms.text.password" /></label> 
			<input type="password"	class="form-control" id="password" name="password"
			placeholder="Password">
	</div>
	<div class="input-group input-sm">
		<div class="checkbox">
			<label for="rememberme">
			<input type="checkbox" id="rememberme"
				name="remember-me" placeholder="Rememberme"><spring:message
				code="crsms.text.rememberme" />
				</label>
		</div>
	</div>

	<button type="submit" class="btn btn-default">
		<spring:message code="crsms.button.log.in" />
	</button>

	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />

</form> --%>