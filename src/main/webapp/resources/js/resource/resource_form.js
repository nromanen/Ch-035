$(document).ready(function(e) {	
	
	var resourceFormHelper = {
		getResources: function() {
			$.ajax({
				url: $("#base-link").val() + "api/resources",
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
									<button class="btn btn-primary" \
											data-toggle="tooltip" \
											title="' + $("#spring-msg-add-existing").val() + '" >\
										<i class="fa fa-lg fa-plus"></i>\
									</button>\
								</td>\
							</tr>\
							'
						);
					}
					resourceFormHelper.updateToolTips();
				},
				error: function(xhr, ajaxOptions, thrownError) {
					
				}
			});
		},
		updateToolTips: function() {
			$('#tab-existing-resources table tbody [data-toggle="tooltip"]').tooltip();
		},
	}
	
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