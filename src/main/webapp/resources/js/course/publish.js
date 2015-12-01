$(document).ready(function() {
	$('#publishModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var courseId = button.data('course-id');
		var url = '/crsms/api/courses/' + courseId + '/groups/';
		$.get(url, function(groups) {
			if (groups.length == 0) {
				$('#no-groups').removeClass('hide');
				$('#confirm-publishing').addClass('hide');
				$('#publish-btn').addClass('hide');
				$('#create-group-btn').removeClass('hide');
				var createGroupUrl = courseId + '/groups/add';
				$('#create-group-btn').attr('href', createGroupUrl);
			} else {
				$('#no-groups').addClass('hide');
				$('#confirm-publishing').removeClass('hide');
				$('#publish-btn').removeClass('hide');
				$('#create-group-btn').addClass('hide');
			}
		})
		
		$('#publish-btn').click(function() {
			var url = courseId + '/publish';
			location.href = url;
		})
	})
	
	$('#publishModal').on('hide.bs.modal', function(event) {
		$('#courses-table-wrapper').focus();
	})
	
})