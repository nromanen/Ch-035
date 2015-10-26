$(document).ready(function(e) {

	// tooltips
	$('[data-toggle="tooltip"]').tooltip();

	// table rows hover
	$(".table tbody tr").hover(function(e) {
		// to avoid bugs reset all rows
		$(".table tbody tr").removeClass("info");
		$(e.delegateTarget).addClass("info");
	}, function(e) {
		$(e.delegateTarget).removeClass("info");
	});

});
