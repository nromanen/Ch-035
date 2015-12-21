<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<input type = "hidden" id = "crsf-token" value = "${_csrf.token}">

<div id = "alert" class="alert alert-info collapse" role="alert">
	<button id="close-alert-btn" type="button" class="close" aria-label="Close"><span aria-hidden="true">×</span></button>
	
	<div id = "all-subscribed">
		<spring:message code="crsms.groups.message.subscribed.all" />
	</div>
	
	<div id = "not-all-subscribed">
		<div>
			<spring:message code="crsms.groups.message.subscribed.count" />
			<span id = "subscribed-users-count"></span>
		</div>
		<spring:message code="crsms.groups.message.not.subscribed" />
		<div id = "not-subscribed-users"></div>
		<spring:message code="crsms.groups.message.not.subscribed.reason" />
	</div>
	
	<div id = "no-email-chosen">
		<spring:message code="crsms.groups.message.no.emails" />
	</div>
	
	<div id = "wrong-email-alert">
		<spring:message code="crsms.groups.message.wrong.email" />
		<span id = "wrong-email"></span>
	</div>
</div>

<!-- Nav tabs -->
<ul class="nav nav-tabs" role="tablist">
	<li role="presentation" class="active">
		<a id="by-email-tab"
		   href="#by-email-pane"
		   aria-controls="by-email-pane"
		   role="tab"
		   data-toggle="tab">
			<spring:message code = "crsms.groups.text.by.email"/>
		</a>
	</li>
	<li role="presentation">
		<a id = "from-other-group-tab"
		   href="#from-other-group-pane"
		   aria-controls="from-other-group-pane"
		   role="tab"
		   data-toggle="tab">
			<spring:message code = "crsms.groups.text.from.other.group"/>
		</a>
	</li>
	<li role="presentation">
		<a id="search-tab"
		   href="#search-pane"
		   aria-controls="search-pane"
		   role="tab"
		   data-toggle="tab">
			<spring:message code = "crsms.button.search"/>
		</a>
	</li>
</ul>

<!-- Tab panes -->
<div class="tab-content">
	<!-- By email -->
	<div role="tabpanel" class="tab-pane fade in active active" id="by-email-pane">
		<h5><spring:message code = "crsms.groups.message.by.email"/></h5>
		<textarea id = "emails" rows = "12" class="form-control" autofocus></textarea>
		
		<button id="submit-by-email-btn" type="button" class="submit-btn btn btn-primary">
			<spring:message code = "crsms.groups.button.submit"/>
		</button>
	</div>
	
	<!-- From other group -->
	<div role="tabpanel" class="tab-pane fade" id="from-other-group-pane">
		<h5><spring:message code = "crsms.groups.message.from.other.group"/></h5>
		<select id = "groups" class = "form-control"></select>
		
		<select id = "students-from-other-group" class="form-control" size = "9" multiple></select>
		
		<div class = "half-block">
			<button id = "select-all-btn" class = "btn btn-sm btn-default form-control">
				<spring:message code = "crsms.groups.button.select.all"/>
			</button>
		</div>
		<div class = "half-block">
			<button id = "clear-selection-btn" class = "btn btn-sm btn-default form-control">
				<spring:message code = "crsms.groups.button.select.clear"/>
			</button>
		</div>
		
		<button id="submit-from-other-group-btn" type="button" class="submit-btn btn btn-primary">
			<spring:message code = "crsms.groups.button.submit"/>
		</button>
	</div>
	
	<!-- Search -->
	<div role="tabpanel" class="tab-pane fade" id="search-pane">
		<h5><spring:message code = "crsms.groups.message.search"/></h5>
		<div class = "input-group">
			<div class = "form-group has-feedback">
				<input id = "search" type="search" class="form-control">
				<span id = "clear-search" class="glyphicon glyphicon-remove form-control-feedback"></span>
			</div>
			<span class = "input-group-btn">
				<button id = "serch-btn" class = "btn btn-default">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</span>
		</div>
		
		<select id = "students-from-search" class = "form-control" size = "10" multiple></select>
		
		<button id="submit-search-btn" type="button" class="submit-btn btn btn-primary">
			<spring:message code = "crsms.groups.button.submit"/>
		</button>
	</div>
</div>
