



$(document).ready(function(e) {
	
	// delete resource
	$(".btn-delete-resource").click(function(e) {
		// clean
		$("#delete-confirmation-modal").find(".resource-delete-msg").html("");
		// hide tooltip
		$(e.delegateTarget).tooltip('hide');
		// add delete rest
		$("#btn-modal-delete-resource").attr("href", 
			e.delegateTarget.value + "/delete"
			);
		// append delete msg to modal		
		var resourceRow = $(e.delegateTarget).parents("tr");
		var resourceName = resourceRow.children(".resource-name").html();
		$("#delete-confirmation-modal").find(".resource-delete-msg").
			append("\"" + resourceName + "\"");
		
		$("#delete-confirmation-modal").modal('show');
	});
	
	// end
	
	// edit resource
	$(".btn-edit-resource").click(function(e) {
		$(e.delegateTarget).tooltip('hide');
		
		$("#modal-edit-resource").modal('show');
		crsmsResourceHelper.getResourceById(e.delegateTarget.value);
	});
	
	// submit
	$("#btn-modal-edit-resource").click(function(e) {
		$("#modal-edit-resource-body-form").submit();	
	});
	
	crsmsResourceHelper = {
		hideModalBodyEls: function() {
			$("#modal-edit-resource-body-error").addClass("hide");
			$("#modal-edit-resource-body-form").addClass("hide");
			$("#modal-edit-resource-body-loading").addClass("hide");
			$("#btn-modal-edit-resource").addClass("hide");
		},
		getResourceById: function(id) {
			$.ajax({
				url: $("#link-base").val() + "api/resources/" + id,
				type: 'get',
				dataType: 'json',
				beforeSend : function () {
					crsmsResourceHelper.hideModalBodyEls();
					$("#modal-edit-resource-body-loading").removeClass("hide");
				},
				complete : function () {

				},
				success: function(resource) {
					crsmsResourceHelper.hideModalBodyEls();
					var resourceForm = $("#modal-edit-resource-body-form");					
					$("#modal-edit-resource-input-name").val(resource.name);
					$("#modal-edit-resource-input-type-hidden").val(resource.type);
					$("#modal-edit-resource-div-type").html(resource.type);
					$("#modal-edit-resource-input-storage-type").val(resource.storageType);
					$("#modal-edit-resource-input-id").val(resource.id);
					$("#modal-edit-resource-input-path").val(resource.path);
					resourceForm.attr("action", id + "/edit");
					resourceForm.removeClass("hide");
					$("#btn-modal-edit-resource").removeClass("hide");
				},
				error: function(xhr, ajaxOptions, thrownError) {
					crsmsResourceHelper.hideModalBodyEls();
					$("#modal-edit-resource-body-error").removeClass("hide");
				}
			});
		},
	}
	// end
	
});