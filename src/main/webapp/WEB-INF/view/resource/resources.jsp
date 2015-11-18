<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>

<c:url var = "editResourceLink" value = "${resource.id}/edit" />
<c:url var = "linkBase" value = "/" />
<c:url var = "addResourceLink" value = "add" />

<input id="link-base" type="hidden" value="${linkBase}">

<c:choose>
  <c:when test="${not empty param.errorPermission}">
    <div class="alert alert-danger alert-dismissible fade in" role="alert">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
      <strong><spring:message code = "crsms.text.error" />!</strong> <spring:message code = "crsms.resource.error.permission.denied" />
    </div>
  </c:when>

</c:choose>

<a class = "btn btn-primary" href = "${addResourceLink}"><spring:message code = "crsms.resource.button.add" /></a>

<table class = "table table-bordered table-hover">
	<thead>
		<tr class = "active">
			<th class="hide text-center"><spring:message code = "crsms.text.id" /></th>
			<th class="text-center"><spring:message code = "crsms.text.name" /></th>
			<th class="text-center"><spring:message code = "crsms.resource.text.type" /></th>
			<th class="text-center"><spring:message code = "crsms.text.url" /></th>
			<th class="text-center"><spring:message code = "crsms.text.controls" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var = "resource" items = "${resources}">
			<tr>
				<td class="resource-id hide" >${resource.id}</td>
				<td class="resource-name" >${resource.name}</td>
				<td>${resource.type}</td>
				<td>${resource.url}</td>
				<td>
					<div class="text-center">
					  <button class="btn btn-primary btn-sm btn-edit-resource" 
					  		data-toggle="tooltip" title="<spring:message code = "crsms.resource.button.edit" />" 
					  		value="${resource.id}"
					  >
					    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					  </button>	
					  

					  <button class="btn btn-danger btn-sm btn-delete-resource" 
					  		 data-toggle="tooltip" title="<spring:message code = "crsms.resource.button.delete" />" 
					  		 value="${resource.id}" >
					    <i class="fa fa-trash-o fa-lg" aria-hidden="true"></i>
					  </button>

					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a class = "btn btn-primary" href = "${addResourceLink}"><spring:message code = "crsms.resource.button.add" /></a>

<!-- Modal delete confirmation -->
<div class="modal fade" id="delete-confirmation-modal" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><spring:message code = "crsms.resource.text.confirmation" /></h4>
      </div>
      <div class="modal-body">
        <p style="font-size: 15px; font-weight: 600;">
        	<spring:message code = "crsms.resource.msg.confirm.delete" /> 
        	<span class="resource-delete-msg"></span>
        </p>
      </div>
      <div class="modal-footer">
      	<a href="#" id="btn-modal-delete-resource" class="btn btn-danger"><spring:message code = "crsms.button.delete" /></a>
        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code = "crsms.button.cancel" /></button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- Modal edit -->
<div class="modal fade" id="modal-edit-resource" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><spring:message code = "crsms.resource.text.resource.edit" /></h4>
      </div>
      <div class="modal-body" class="modal-resource-body">
        <p id="modal-edit-resource-body-loading" style="font-size: 16px; font-weight: 700;" >
        	<spring:message code = "crsms.text.loading" />...
        </p>
		
		<form:form id="modal-edit-resource-body-form" action="" modelAttribute = "resource" method = "POST" class = "form-horizontal">
			<input name = "id" id="modal-edit-resource-input-id" type = "hidden"/>
			
			<div class="form-group" >
				<label for = "type" class="col-sm-3 control-label"><spring:message code = "crsms.resource.text.type" />: </label>
				<div class="col-sm-9">
					<input type="hidden" name="type" id = "modal-edit-resource-input-type-hidden" class="form-control" />
					<div id = "modal-edit-resource-div-type" class="form-control resource-form-control-disabled"></div>
				</div>
			</div>
			
			<div class="form-group">
				<label for = "name" class="col-sm-3 control-label"><spring:message code = "crsms.text.name" />: </label>
				<div class="col-sm-9">
					<spring:message code="crsms.text.name" var="namePlaceholder"/>
					<input id="modal-edit-resource-input-name" name = "name"
								class="form-control" placeholder="${namePlaceholder}"/>
				</div>
			</div>
			
			<div class="form-group hide">
				<label for = "url" class="col-sm-3 control-label"><spring:message code = "crsms.text.url" />: </label>
				<div class="col-sm-9">
					<spring:message code="crsms.text.link" var="linkPlaceholder"/>
				    <input id="modal-edit-resource-input-url" name = "url"
								class="form-control" placeholder="${linkPlaceholder}"/>
				</div>
			</div>
			
			<div class="form-group hide">
				<div class="col-sm-offset-1 col-sm-4">
					<input type = "submit" value = "<spring:message code = "crsms.button.save" />" class="btn btn-primary"/>
				</div>
			</div>		
		</form:form>


        <div id="modal-edit-resource-body-error" class="text-danger" >
        	<h3><spring:message code = "crsms.text.error" />:</h3>
        	<spring:message code = "crsms.resource.msg.error.get" />
        </div>
      </div>
      <div class="modal-footer">
      	<button value="" id="btn-modal-edit-resource" class="btn btn-primary"><spring:message code = "crsms.button.save" /></button>
        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code = "crsms.button.cancel" /></button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


