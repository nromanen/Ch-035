$(document).ready(function() {
	$('#subscribeModal').on('show.bs.modal', function(e) {
		var button = $(e.relatedTarget);
		var courseId = button.data('course-id');
		var modal = $(this);
		var url = '/crsms/api/courses/' + courseId + '/groups/';
		$.get(url, function(groups) {
			modal.find('#select-group').empty();
			for (var i = 0; i < groups.length; i++) {
				var optionHtml = '<option value="' + groups[i].id + '">' + groups[i].name + '</option>'
				modal.find('#select-group').append(optionHtml);
			}
		})
		
		$('#btn-enroll').click(function() {
			var groupId = $('#select-group').val();
			location.href = '/crsms/courses/' + courseId + '/groups/' + groupId + '/enroll';
		})
	})
	
	$('#unsubscribeModal').on('show.bs.modal', function(e) {
		var button = $(e.relatedTarget);
		var courseId = button.data('course-id');
		var groupId = button.data('group-id');
		
		$('#btn-leave').click(function() {
			location.href = '/crsms/courses/' + courseId + '/groups/' + groupId + '/leave';
		})
	})
})