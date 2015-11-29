$(document).ready(function() {
	$('#addStudentModal').on('show.bs.modal', function(event) {
		var modal = $(this);
		var url = '/crsms/api/groups/';
		var groupsList = modal.find('#groups');
		$.get(url, function(groups) {
			cleanFields();
			for (var i = 0; i < groups.length; i++) {
				var optionHtml = '<option value="' + groups[i].id + '">' + groups[i].name + '</option>'
				groupsList.append(optionHtml);
			}
		})
		
		$('#btn-enroll').click(function() {
			var groupId = $('#select-group').val();
			location.href = '/crsms/courses/' + courseId + '/groups/' + groupId + '/enroll';
		})
	})
	
	function cleanFields() {
		var groupSelect = $('#groups');
		var studentSelect = $('#students');
		groupSelect.empty();
		studentSelect.empty();
		var noneGroupHtml = '<option value = "-1">--</option>';
		groupSelect.append(noneGroupHtml);
	}
	
	$('#groups').change(function() {
		var groupId = $('#groups').val();
		var studentsSelect = $('#students');
		
		if (groupId == -1) {
			studentsSelect.empty();
			alert("wtf?");
		} else {
			var url = '/crsms/api/groups/' + groupId + '/students/';
			$.get(url, function(students) {
				studentsSelect.empty();
				for (var i = 0; i < students.length; i++) {
					var studentOptionHtml = '<option value = "' + students[i].id + '">' + students[i].email + '</option>';
					studentsSelect.append(studentOptionHtml);
				}
			})
		}
	})
	
	$('#students').change(function() {
		alert("qwe");
	})
})