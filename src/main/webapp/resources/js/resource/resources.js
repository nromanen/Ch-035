$(document).ready(function(e) {
	
	// table rows hover
	$(".table tbody tr").hover(
		function(e) {
			// to avoid bugs reset all rows
			$(".table tbody tr").removeClass("info");
			$(e.delegateTarget).addClass("info");
		},
		function(e) {
			$(e.delegateTarget).removeClass("info");
		}
	);
	
	// 
	$(".btn-delete-resource").click(function(e) {
		$(e.delegateTarget).tooltip('hide');
		$("#btn-modal-delete-resource").attr("href", 
			e.delegateTarget.value + "/delete"
			);
		$("#delete-confirmation-modal").modal('show');
	});
	
});