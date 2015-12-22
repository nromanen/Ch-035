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
		filtersQuery: {}, 
		showResourcesNotAssociatedWithModule: function() {
			var targetTableBody = $("#tab-existing-resources table tbody");
			$.ajax({
				url: crsmsGlobalResourceFormHelper.baseLink 
						+ "api/resources/notAssociatedWith/modules/" + resourceFormHelper.getModuleId()
						+ "?" + $.param(resourceFormHelper.filtersQuery),
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
								<td>' + resources[i].path + '</td>\
								<td class="text-center">\
									<button class="btn btn-primary btn-add-existing-resource" \
											resource-id="' + resources[i].id + '" \
											data-toggle="tooltip" \
											title="' + springMsgs.addExisting + '" >\
										<i class="fa fa-lg fa-plus"></i>\
									</button>\
									<button class="btn btn-primary btn-show-associated" \
											resource-id="' + resources[i].id + '" \
											resource-name="' + resources[i].name + '" \
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
				resourceFormHelper.showAssociatedWithResource(this.getAttribute("resource-id"), this);
			});
		},
		genericAlertTimeout: 3000,
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
					resourceFormHelper.showAlert({ msg: springMsgs.successAdd, 
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
							  type: "danger", title: springMsgs.error, 
							  autoclosable: true });
					//alert(thrownError + ": " + xhr.responseText);
				}
			});
		},
		modalResourceAssociatedSelector: "#modal-resource-associated", 
		showAssociatedWithResource: function(resourceId, delegatedBtnNode) {
			var modalSelector = resourceFormHelper.modalResourceAssociatedSelector;
			var modalUlSelector = modalSelector + " .modal-body ul";
			var modalBodyTitleSelector = modalSelector + " .modal-body-title";
			$.ajax({
				url: crsmsGlobalResourceFormHelper.baseLink + "api/resources/" + resourceId + "/associated",
				type: 'get',
				data: { "_csrf": crsmsGlobalResourceFormHelper.crsfToken },
				beforeSend: function() {
					$(delegatedBtnNode).html('<i class="fa fa-lg fa-spinner fa-spin"></i>');
					$("#sticker-alert-container .alert").alert('close');
					$(modalUlSelector).html("");
				},
				complete: function() {
					$(delegatedBtnNode).html('<i class="fa fa-lg fa-list-ul"></i>');
				},
				success: function(associated) {
					var modalUl = $(modalUlSelector);
					var modalBodyTitle= $(modalBodyTitleSelector);
					if (associated.length < 1) {
						modalBodyTitle.html(springMsgs.noResultsForAssociated);
					} else {
						modalBodyTitle.html(springMsgs.course + ' <i class="fa fa-lg fa-long-arrow-right"></i> '
								+ springMsgs.module + ":");
						for (var i = 0; i < associated.length; i++) {
							modalUl.append(''
									+ '<li>'
									+ associated[i].courseName 
									+ ' <i class="fa fa-lg fa-long-arrow-right"></i> ' 
									+ associated[i].moduleName
									+ '</li>'
									);
						}
					}
					$(delegatedBtnNode).tooltip('hide');
					$(modalSelector).modal('show');
				},
				error: function(xhr, ajaxOptions, thrownError) {
					resourceFormHelper.showAlert(
							{ msg: springMsgs.errorAdd + "<br/>" + thrownError + ": " + xhr.responseText, 
							  type: "danger", title: springMsgs.error, 
							  autoclosable: true });
				}
			});
		},
		alertContainerSelector: "#sticker-alert-container",
		showAlert: function(params) {
			params.target = typeof params.target !== 'undefined' ? params.target : resourceFormHelper.alertContainerSelector;
			params.type = typeof params.type !== 'undefined' ? params.type : "success";
			params.title = typeof params.title !== 'undefined' ? params.title : "Alert";
			params.msg = typeof params.msg !== 'undefined' ? params.msg : "";
			params.autoclosable = typeof params.autoclosable !== 'undefined' ? params.autoclosable : true;
			params.timeOutMillis = typeof params.timeOutMillis !== 'undefined' ? 
					params.timeOutMillis : resourceFormHelper.genericAlertTimeout;
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
		uncheckTypeFilters: function(checkedType) {
			var chekboxes = $('.resource-filters .checkbox input[type="checkbox"]');
			for (var i = 0; i < chekboxes.length; i++) {
				chekboxes[i].checked = $(chekboxes[i]).attr('resource-type') == checkedType ? true : false;
			}
		},
	}
	
	// show existing resources
	$('a[href="#tab-existing-resources"]').on('shown.bs.tab', function (e) {
		if (!$(this).attr("crsms-ajax-executed")) {
			resourceFormHelper.showResourcesNotAssociatedWithModule();
			$(this).attr("crsms-ajax-executed", true);
		}
	});
	// filters
	// type checkbox
	$('.resource-filters .checkbox input[type="checkbox"]').change(function(e) {
		if (this.checked) {
			resourceFormHelper.uncheckTypeFilters($(this).attr("resource-type"));
			resourceFormHelper.filtersQuery.type = $(this).attr("resource-type");
		} else {
			delete resourceFormHelper.filtersQuery.type;
		}
		resourceFormHelper.showResourcesNotAssociatedWithModule();
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