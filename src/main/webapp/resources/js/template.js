$(document).ready(function(e) {
	
	// tooltips
	$('[data-toggle="tooltip"]').tooltip();

	// breadcrumbs
	var breadcrumb = $('#breadcrumb');
	var path = $(location).attr('pathname');
	var crumbs = path.replace(/^./, '')
					 .replace(/.$/, '')
					 .replace(/\/\d\//g, '/')
					 .split("/");
	
	for (var i = 0; i < crumbs.length - 1; i++ ) {
		breadcrumb.append(makeCrumb(crumbs[i]));
	}
	
	breadcrumb.append(makeActiveCrumb(crumbs[crumbs.length - 1]));
	
	function makeCrumb(crumb) {
		return '<li><a href="#">' + crumb + '</a></li>'
	}
	
	function makeActiveCrumb(crumb) {
		return '<li class = "active">' + crumb + '</li>';
	}
	
	var pattern = /(\d\/)*\w+\//g;
	
	var parts = path.match(pattern);
	var partss = 0;
	do {
		var result = pattern.exec(path);
		if (result)
		parts.push(pattern.exec(path)[0]);
	} while (parts != null);
	
	function makeCrumbLink(crumb) {
		
	}
});