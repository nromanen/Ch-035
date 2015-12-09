$(document).ready(function() {
	$('.clickable').click(function() {
		var groupId = $(this).attr('id');
		location.href = groupId + "/students/";
	})
})