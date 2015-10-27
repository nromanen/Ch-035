<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:url var = "addResourceLink" value = "add" />
<a class = "btn btn-primary" href = "${addResourceLink}"><spring:message code = "crsms.resource.button.add" /></a>

<table class = "table table-bordered">
	<thead class = "">
		<tr>
			<th><spring:message code = "crsms.text.id" /></th>
			<th><spring:message code = "crsms.text.name" /></th>
			<th><spring:message code = "crsms.resource.text.type" /></th>
			<th><spring:message code = "crsms.text.url" /></th>
			<th><spring:message code = "crsms.text.controls" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var = "resource" items = "${resources}">
			<tr>
				<td class="resource-id" >${resource.id}</td>
				<td class="resource-name" >${resource.name}</td>
				<td>${resource.type}</td>
				<td>${resource.url}</td>
				<td>
					<div class="btn-toolbar" role="toolbar">
					  <c:url var = "editResourceLink" value = "${resource.id}/edit" />
					  <a href = "${editResourceLink}" class="btn btn-primary btn-sm" 
					  		data-toggle="tooltip" title="<spring:message code = "crsms.resource.button.edit" />" >
					    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					  </a>	
					  

					  <button class="btn btn-danger btn-sm btn-delete-resource" 
					  		 data-toggle="tooltip" title="<spring:message code = "crsms.resource.button.delete" />" 
					  		 value="${resource.id}" >
					    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					  </button>

					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<!-- Modal delete confirmation -->
<div class="modal fade" id="delete-confirmation-modal" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><spring:message code = "crsms.resource.text.confirmation" /></h4>
      </div>
      <div class="modal-body">
        <p class="resource-delete-msg" style="font-size: 15px; font-weight: 600;"><spring:message code = "crsms.resource.msg.confirm.delete" /> </p>
      </div>
      <div class="modal-footer">
      	<a href="#" id="btn-modal-delete-resource" class="btn btn-danger"><spring:message code = "crsms.button.delete" /></a>
        <button type="button" class="btn btn-primary" data-dismiss="modal"><spring:message code = "crsms.button.cancel" /></button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<a class = "btn btn-primary" href = "${addResourceLink}"><spring:message code = "crsms.resource.button.add" /></a>


