<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:choose>
  <c:when test="${not empty success}">
    <div class="alert alert-success alert-dismissible fade in" role="alert">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
      <strong><spring:message code = "crsms.text.success" />!</strong> <spring:message code = "crsms.resource.msg.success.add" />
    </div>
  </c:when>
  <c:when test="${not empty error}">
    <div class="alert alert-danger alert-dismissible fade in" role="alert">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
      <strong><spring:message code = "crsms.text.error" />!</strong> <spring:message code = "crsms.resource.msg.error.add" />
    </div>
  </c:when>
</c:choose>

<div>

  <!-- Nav tabs -->
  <ul class="nav nav-pills" role="tablist">
    <li role="presentation" class="active">
    	<a href="#tab-type-embedded" aria-controls="tab-type-embedded" role="tab" data-toggle="tab">
    		<spring:message code = "crsms.resource.text.embedded" />
    	</a>
    </li>
    <li role="presentation"><a href="#tab-type-file" aria-controls="tab-type-file" role="tab" data-toggle="tab">
    	<spring:message code = "crsms.resource.text.file" /></a>
    </li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content" style="margin-top: 20px;">
    <div role="tabpanel" class="tab-pane fade in active" id="tab-type-embedded">
		<form:form action="addembedded" modelAttribute = "resource" method = "POST" class = "form-horizontal">
			<form:input path = "id" type = "hidden"/>
			
			<div class="form-group hidden">
				<label for = "type" class="col-sm-1 control-label"><spring:message code = "crsms.resource.text.type" />: </label>
				<div class="col-sm-4">
					<form:input path = "type"
								class="form-control" value="${resourceTypeEmbedded}"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for = "name" class="col-sm-1 control-label"><spring:message code = "crsms.text.name" />: </label>
				<div class="col-sm-4">
					<spring:message code="crsms.text.name" var="namePlaceholder"/>
					<form:input path = "name"
								class="form-control" placeholder="${namePlaceholder}"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for = "url" class="col-sm-1 control-label"><spring:message code = "crsms.text.url" />: </label>
				<div class="col-sm-4">
					<spring:message code="crsms.text.link" var="linkPlaceholder"/>
				    <form:input path = "url"
								class="form-control" placeholder="${linkPlaceholder}"/>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-4">
					<input type = "submit" value = "<spring:message code = "crsms.button.save" />" class="btn btn-success"/>
				</div>
			</div>		
		</form:form>
	</div>
	
    <div role="tabpanel" class="tab-pane fade" id="tab-type-file">
    	<form:form method="POST" action="addfile" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">
			<div class="form-group">
				<label for = "type" class="col-sm-1 control-label"><spring:message code = "crsms.resource.text.file" />: </label>
				<div class="col-sm-4">
					<form:input type="file" path="file" id="file" class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-4">
					<input type = "submit" value = "<spring:message code = "crsms.button.save" />" class="btn btn-success"/>
				</div>
			</div>	
		</form:form>
    </div>
  </div>

</div>




