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
			$.ajax({
				url: crsmsGlobalResourceFormHelper.baseLink 
						+ "api/resources/notAssociatedWith/modules/" + resourceFormHelper.getModuleId(),
				type: 'get',
				dataType: 'json',
				beforeSend: function() {
					$("#tab-existing-resources table tbody").html("");
				},
				success: function(resources) {
					for (var i = 0; i < resources.length; i++) {
						$("#tab-existing-resources table tbody").append(
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
								</td>\
							</tr>\
							'
						);
					}
					resourceFormHelper.updateToolTips();
					resourceFormHelper.addExistingResourceBtnHandlers();
				},
				error: function(xhr, ajaxOptions, thrownError) {
					alert(thrownError);
				}
			});
		},
		updateToolTips: function() {
			$('#tab-existing-resources table tbody [data-toggle="tooltip"]').tooltip();
		},
		addExistingResourceBtnHandlers: function() {
			// add existing resource
			$('.btn-add-existing-resource').click(function(e) {
				if ($(this).hasClass("disabled")) { return; }
				resourceFormHelper.addExistingResource(this.getAttribute("resource-id"), this);
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
					$("#sticker-alert-container .alert").alert('close');
					$(delegatedBtnNode).html('<i class="fa fa-lg fa-spinner"></i>');
				},
				success: function() {
					resourceFormHelper.showAlert("#sticker-alert-container", "success", springMsgs.success, 
							springMsgs.successAdd, true);
					$(delegatedBtnNode).html('<i class="fa fa-lg fa-check"></i>');
					$(delegatedBtnNode).addClass("disabled");
					$(delegatedBtnNode).tooltip('destroy');
					$(delegatedBtnNode).closest('tr').addClass("bg-success");
				},
				error: function(xhr, ajaxOptions, thrownError) {
					$(delegatedBtnNode).html('<i class="fa fa-lg fa-plus"></i>');
					resourceFormHelper.showAlert("#sticker-alert-container", "danger", springMsgs.error, 
							springMsgs.errorAdd + "<br/>" + thrownError + ": " + xhr.responseText, true);
					//alert(thrownError + ": " + xhr.responseText);
				}
			});
		},
		showAlert: function(target, type, title, msg, autoclosable) {
			var alertHtml = 
				'\
				<div class="alert alert-' + type + ' alert-dismissible fade in" role="alert"> \
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button> \
				  <strong>' + title + '!</strong> ' + msg + ' \
				</div>\
				';
			$(target).prepend(alertHtml);
			if (autoclosable) {
				setTimeout(function() { 
					$(target + " .alert").alert('close');
				}, resourceFormHelper.addResourceAlertTimeout);
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