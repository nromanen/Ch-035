<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/taglib/CrsmsTaglib.tld" prefix="crsms" %>

<tiles:insertAttribute name="jquery-validation-messages"></tiles:insertAttribute>

<c:url var = "baseLink" value = "/" />

<script type="text/javascript">
	var crsmsGlobalResourceFormHelper = {
		baseLink: "${baseLink}",
		crsfToken: "${_csrf.token}",
		springLocalizationMsgs: {
			addExisting: '<spring:message code = "crsms.resource.text.existing.add" />',
			success: '<spring:message code = "crsms.text.success" />',
			error: '<spring:message code = "crsms.text.error" />',
			successAdd: '<spring:message code = "crsms.resource.msg.success.add" />',
			errorAdd: '<spring:message code = "crsms.resource.msg.error.add" />',
			showAssociated: '<spring:message code = "crsms.resource.text.show.associated" />',
			noResults: '<spring:message code = "crsms.text.no.results" />',
			course: '<spring:message code = "crsms.text.course" />',
			module: '<spring:message code = "crsms.text.module" />',
			noResultsForAssociated: '<spring:message code = "crsms.resource.text.associated.no.results" />',
			searchInfo: '<spring:message code = "crsms.resource.text.search.info" />',
			searchInfoTitle: '<spring:message code = "crsms.text.info" />',
		},
	}
</script>

<div id="sticker-alert-container"></div>

<c:choose>
  <c:when test="${not empty param.success}">
    <div class="alert alert-success alert-dismissible fade in" role="alert">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
      <strong><spring:message code = "crsms.text.success" />!</strong> <spring:message code = "crsms.resource.msg.success.add" />
    </div>
  </c:when>
  <c:when test="${not empty param.error}">
    <div class="alert alert-danger alert-dismissible fade in" role="alert">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
      <strong><spring:message code = "crsms.text.error" />!</strong> <spring:message code = "crsms.resource.msg.error.add" />
    </div>
  </c:when>
</c:choose>

<div id="resources-forms">

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active">
    	<a href="#tab-type-embedded" aria-controls="tab-type-embedded" role="tab" data-toggle="tab">
    		<spring:message code = "crsms.resource.text.embedded" />
    	</a>
    </li>
    <li role="presentation">
    	<a href="#tab-type-file" aria-controls="tab-type-file" role="tab" data-toggle="tab">
    		<spring:message code = "crsms.resource.text.file" />
    	</a>
    </li>
    <c:if test="${moduleContextPath}">
    <li role="presentation">
    	<a href="#tab-existing-resources" aria-controls="tab-existing-resources" role="tab" data-toggle="tab">
    		<spring:message code = "crsms.resource.text.existing" />
    	</a>
    </li>
    </c:if>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content" style="margin-top: 20px;">
    <div role="tabpanel" class="tab-pane fade in active" id="tab-type-embedded">
		<form:form action="addembedded" id="embedded-form" modelAttribute = "resource" method = "POST" class = "form-horizontal">
			<form:input path = "id" type = "hidden"/>
			
			<div class="form-group hidden">
				<label for = "type" class="col-sm-1 control-label"><spring:message code = "crsms.resource.text.type" />: </label>
				<div class="col-sm-4">
					<form:input path = "type"
								class="form-control" value="${resourceTypeEmbedded}"/>
				</div>
			</div>
			
			<form:input path = "storageType" type="hidden"
								class="form-control" value="${resourceStorageTypeDB}"/>
			
			<div class="form-group">
				<label for = "name" class="col-sm-1 control-label"><spring:message code = "crsms.text.name" />: </label>
				<div class="col-sm-4">
					<spring:message code="crsms.text.name" var="namePlaceholder"/>
					<form:input path = "name" name="name"
								class="form-control" placeholder="${namePlaceholder}"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for = "path" class="col-sm-1 control-label"><spring:message code = "crsms.resource.text.embedded.src" />: </label>
				<div class="col-sm-4">
					<spring:message code="crsms.text.link" var="linkPlaceholder"/>
				    <form:input path = "path"
								class="form-control" placeholder="${linkPlaceholder}"/>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-4">
					<input type = "submit" value = "<spring:message code = "crsms.button.save" />" class="btn btn-primary"/>
				</div>
			</div>		
		</form:form>
	</div>
	
    <div role="tabpanel" class="tab-pane fade" id="tab-type-file">
    	<form:form method="POST" action="uploadfile" id="file-form" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">
			<div class="form-group">
				<label for = "type" class="col-sm-1 control-label"><spring:message code = "crsms.resource.text.file" />: </label>
				<div class="col-sm-4">
					<form:input type="file" path="file" id="file" name="file" class="form-control" style="height: auto;" />
					<form:errors path="file" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-4">
					<input type = "submit" value = "<spring:message code = "crsms.button.save" />" class="btn btn-primary"/>
				</div>
			</div>	
		</form:form>
    </div>
    
    <c:if test="${moduleContextPath}">
    <div role="tabpanel" class="tab-pane fade" id="tab-existing-resources">
    	<div class="resource-filters">
    		<div><strong><spring:message code = "crsms.text.filters" />:</strong></div>
    		<div class="col-sm-4">
    			<div class="checkbox">
				    <label>
				      <input type="checkbox" resource-type="EMBEDDED"> <spring:message code = "crsms.resource.text.embedded" />
				    </label>
				</div>
				<div class="checkbox">
				    <label>
				      <input type="checkbox" resource-type="FILE"> <spring:message code = "crsms.resource.text.file" />
				    </label>
				</div>
    		</div>
    		<div id="search-container" class = "input-group col-sm-8">
				<span class = "input-group-btn">
					<button id = "search-btn" class = "btn btn-default">
						<i class="fa fa-lg fa-search"></i>
					</button>
				</span>
				<input id = "search" type="search" class="form-control" 
						placeholder="<spring:message code = "crsms.button.search" />">
				<input id="search-info-popover-landing" type="text" class="form-control" />
			    <span class="input-group-btn">
					<button id="search-info-btn" class="btn btn-default" type="button">
			    		<i class="fa fa-lg fa-info"></i>
			    	</button>
			    	<button id = "clear-search" class="btn btn-default" type="button">
			    		<i class="fa fa-lg fa-times"></i>
			    	</button>
			    </span>
			</div>
    	</div>
   		<table class = "table table-bordered table-hover">
			<thead>
				<tr class = "active">
					<th class="text-center"><spring:message code = "crsms.text.name" /></th>
					<th class="text-center"><spring:message code = "crsms.resource.text.type" /></th>
					<th class="text-center"><spring:message code = "crsms.resource.text.path" /></th>
					<th class="text-center"><spring:message code = "crsms.text.controls" /></th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
    </div>
    </c:if>
    
  </div>

</div>

<!-- Modal edit -->
<div class="modal fade" id="modal-resource-associated" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><spring:message code = "crsms.resource.text.associated" /></h4>
      </div>
      <div class="modal-body">
        <h4 class="modal-body-title"></h4>
        <ul id="resource-associated-ul"></ul>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->




