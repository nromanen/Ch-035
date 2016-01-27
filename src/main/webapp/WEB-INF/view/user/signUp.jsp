<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tiles:insertAttribute name="jquery-validation-messages"></tiles:insertAttribute>
	<div class="container">
		<div class="row">
			<div class="col-md-offset-3">
				<form:form modelAttribute="userRegistr" id="registration-form"  action="submitUser" name="registration" method="POST" class="form-horizontal">
				
					<div class="form-group">
						<label for="email" class="col-md-3"><spring:message code = "crsms.signUp.email"/></label> 
						<div class="col-md-4">
							<form:input path="email" type="email" class="form-control" id="email" name="email" placeholder="Email" />
							<form:errors path = "email" cssClass = "label label-danger" />
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-md-3"><spring:message code = "crsms.signUp.password"/></label> 
						<div class="col-md-4">
							<span title="<spring:message code = "crsms.signUp.password.tip.title"/>"
							data-toggle="popover" data-trigger="focus"
							data-content="<spring:message code = "crsms.signUp.password.tip.text"/>">
							<form:input path="password" type="password" class="form-control" id="password"
							name="password" placeholder="Password" required="true"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" />
							<form:errors path = "password" cssClass = "label label-danger" /> </span>
							<label id="password-error" class="errorTxt" for="password"></label>
						</div>
					</div>
					<div class="form-group">
						<label for="confirmPassword" class="col-md-3"><spring:message code = "crsms.signUp.confPassword"/></label> 
						<div class="col-md-4">
							<input type="password" class="form-control" id="confPassword" name="confPassword" placeholder="Confirm Password" >
						</div>
					</div>
					<div class="form-group">
						<label for="teacher" class="col-md-3"><spring:message code = "crsms.signUp.teacher"/></label> 
						<div class="col-md-4">
							<div class="checkbox">
								<label> <input type="checkbox" id="teacher" name="teacher"> 
								</label>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-md-offset-3">
						<button type="submit" id="create" class="btn btn-default"> <spring:message code="crsms.signUp.submit" /> </button>
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form:form>
			</div>
		</div>
	</div>