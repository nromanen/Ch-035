<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
  <c:when test="${not empty success}">
    <div class="alert alert-success alert-dismissible fade in" role="alert">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
      <strong>Success!</strong> ${success}
    </div>
  </c:when>
  <c:when test="${not empty error}">
    <div class="alert alert-danger alert-dismissible fade in" role="alert">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
      <strong>Error!</strong> ${error}
    </div>
  </c:when>
</c:choose>

<form:form modelAttribute = "resource" method = "POST" class = "form-horizontal">
	<form:input path = "id" type = "hidden"/>
	
	<div class="form-group">
		<label for = "name" class="col-sm-2 control-label">Name: </label>
		<div class="col-sm-10">
			<form:input path = "name"
						class="form-control" placeholder="Recource name"/>
		</div>
	</div>
	
	<div class="form-group">
		<label for = "type" class="col-sm-2 control-label">Type: </label>
		<div class="col-sm-10">
			<form:select path = "type" name = "type" 
				 items="${resourceTypeValues}" class="form-control"/>
		</div>
	</div>
	
	<div class="form-group">
		<label for = "url" class="col-sm-2 control-label">URL: </label>
		<div class="col-sm-10">
			<form:input path = "url"
						class="form-control" placeholder="http://url"/>
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<input type = "submit" value = "Save" class="btn btn-primary"/>
		</div>
	</div>		
</form:form>

