$(document).ready(function(e) {	
	
	var springMsgs = crsmsGlobalResourceFormHelper.springLocalizationMsgs;
	
	var resourceFormHelper = {
		moduleId: false,
		getModuleId: function() {
			if (this.moduleId) {
				return this.moduleId;
			}
			var moduleIdPattern = /\/modules\/(\d+)\//ig;
			return moduleIdPattern.exec(window.location.href)[1];
		},
		getResources: function() {
			var targetTableBody = $("#tab-existing-resources table tbody");
			$.ajax({
				url: crsmsGlobalResourceFormHelper.baseLink 
						+ "api/resources/notAssociatedWith/modules/" + resourceFormHelper.getModuleId(),
				type: 'get',
				dataType: 'json',
				beforeSend: function() {
					targetTableBody.html("");
					resourceFormHelper.showSpinner('#tab-existing-resources');
				},
				complete: function() {
					resourceFormHelper.hideSpinner('#tab-existing-resources');
				},
				success: function(resources) {
					targetTableBody.html('');
					for (var i = 0; i < resources.length; i++) {
						targetTableBody.append(
							'\
							<tr>\
								<td>' + resources[i].name + '</td>\
								<td>' + resources[i].type + '</td>\
								<td class="text-center">\
									<button class="btn btn-primary btn-add-existing-resource" \
											resource-id="' + resources[i].id + '" \
											data-toggle="tooltip" \
											title="' + springMsgs.addExisting + '" >\
										<i class="fa fa-lg fa-plus"></i>\
									</button>\
									<button class="btn btn-primary btn-show-associated" \
											resource-id="' + resources[i].id + '" \
											data-toggle="tooltip" \
											title="' + springMsgs.showAssociated + '" >\
										<i class="fa fa-lg fa-list-ul"></i>\
									</button>\
								</td>\
							</tr>\
							'
						);
					}
					resourceFormHelper.updateToolTips();
					resourceFormHelper.addBtnHandlers();
				},
				error: function(xhr, ajaxOptions, thrownError) {
					alert(thrownError);
				}
			});
		},
		showSpinner: function(target) {
			$(target).append(
					'<div class="loading-spinner-container">\
						<i class="fa fa-lg fa-spinner fa-spin loading-spinner"></i>\
					</div>');
		},
		hideSpinner: function(target) {
			$(target + ' .loading-spinner-container').remove();
		},
		updateToolTips: function() {
			$('#tab-existing-resources table tbody [data-toggle="tooltip"]').tooltip();
		},
		addBtnHandlers: function() {
			// add existing resource
			$('.btn-add-existing-resource').click(function(e) {
				if ($(this).hasClass("disabled")) { return; }
				resourceFormHelper.addExistingResource(this.getAttribute("resource-id"), this);
			});
			// show associated courses
			$('.btn-show-associated').click(function(e) {
				resourceFormHelper.showAssociatedToResource(this.getAttribute("resource-id"), this);
			});
		},
		addResourceAlertTimeout: 3000,
		addExistingResource: function(resourceId, delegatedBtnNode) {
			$.ajax({
				url: crsmsGlobalResourceFormHelper.baseLink + "api/modules/" + resourceFormHelper.getModuleId()
						+ "/resources/addexisting/" + resourceId,
				type: 'post',
				data: { "_csrf": crsmsGlobalResourceFormHelper.crsfToken },
				beforeSend: function() {
					$(delegatedBtnNode).html('<i class="fa fa-lg fa-spinner fa-spin"></i>');
					$("#sticker-alert-container .alert").alert('close');
				},
				success: function() {
					resourceFormHelper.showAlert({ msg: springMsgs.successAdd, target: "#sticker-alert-container", 
							type: "success", title: springMsgs.success, autoclosable: true });
					$(delegatedBtnNode).html('<i class="fa fa-lg fa-check"></i>');
					$(delegatedBtnNode).addClass("disabled");
					$(delegatedBtnNode).tooltip('destroy');
					$(delegatedBtnNode).closest('tr').addClass("bg-success");
				},
				error: function(xhr, ajaxOptions, thrownError) {
					$(delegatedBtnNode).html('<i class="fa fa-lg fa-plus"></i>');
					resourceFormHelper.showAlert(
							{ msg: springMsgs.errorAdd + "<br/>" + thrownError + ": " + xhr.responseText, 
							  target: "#sticker-alert-container", type: "danger", title: springMsgs.error, 
							  autoclosable: true });
					//alert(thrownError + ": " + xhr.responseText);
				}
			});
		},
		showAssociatedToResource: function(resourceId, delegatedBtnNode) {
			$.ajax({
				url: crsmsGlobalResourceFormHelper.baseLink + "api/resources/" + resourceId + "/associated",
				type: 'get',
				data: { "_csrf": crsmsGlobalResourceFormHelper.crsfToken },
				beforeSend: function() {
					$(delegatedBtnNode).html('<i class="fa fa-lg fa-spinner fa-spin"></i>');
					$("#sticker-alert-container .alert").alert('close');
				},
				complete: function() {
					$(delegatedBtnNode).html('<i class="fa fa-lg fa-list-ul"></i>');
				},
				success: function(courses) {
					alert('sss')
				},
				error: function(xhr, ajaxOptions, thrownError) {
					resourceFormHelper.showAlert(
							{ msg: springMsgs.errorAdd + "<br/>" + thrownError + ": " + xhr.responseText, 
							  target: "#sticker-alert-container", type: "danger", title: springMsgs.error, 
							  autoclosable: true });
				}
			});
		},
		showAlert: function(params) {
			params.target = typeof params.target !== 'undefined' ? params.target : "#sticker-alert-container";
			params.type = typeof params.type !== 'undefined' ? params.type : "success";
			params.title = typeof params.title !== 'undefined' ? params.title : "Alert";
			params.msg = typeof params.msg !== 'undefined' ? params.msg : "";
			params.autoclosable = typeof params.autoclosable !== 'undefined' ? params.autoclosable : true;
			params.timeOutMillis = typeof params.timeOutMillis !== 'undefined' ? 
					params.timeOutMillis : resourceFormHelper.addResourceAlertTimeout;
			var alertHtml = 
				'\
				<div class="alert alert-' + params.type + ' alert-dismissible fade in" role="alert"> \
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button> \
				  <strong>' + params.title + '!</strong> ' + params.msg + ' \
				</div>\
				';
			var alertNode = $(alertHtml).prependTo(params.target);
			if (params.autoclosable) {
				setTimeout(function() { 
					$(alertNode).alert('close');
				}, params.timeOutMillis);
			}
		},
	}
	
	// show existing resources
	$('a[href="#tab-existing-resources"]').on('show.bs.tab', function (e) {
		if (!$(this).attr("crsms-ajax-executed")) {
			resourceFormHelper.getResources();
			$(this).attr("crsms-ajax-executed", true);
		}
	});
	
	// Validation
	$('#embedded-form').validate({
		errorClass: "errorTxt",	
		rules: {
        	"name": {
                required: true,
                minlength: 1,
                maxlength: 100
            },
            "path": {
            	required: true
            },
        },
    });
	
	$('#file-form').validate({
		errorClass: "errorTxt",	
		rules: {
        	"file": {
                required: true
            },
        },
    });
	// End Validation
	
});