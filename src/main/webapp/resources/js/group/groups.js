$(document).ready(function() {
	$('#addStudentModal').on('show.bs.modal', function(e) {
		clearFields();
		
		var button = $(e.relatedTarget);
		var groupId = button.data('group-id');
		$('#submit-btn').val(groupId);
		
		var modal = $(this);
		var url = '/crsms/api/groups/';
		var groupsList = modal.find('#groups');
		
		$.get(url, function(groups) {
			for (var i = 0; i < groups.length; i++) {
				var optionHtml = '<option value="' + groups[i].id + '">' + groups[i].name + '</option>'
				groupsList.append(optionHtml);
			}
		})
	})
	
	function clearFields() {
		$('#emails').val('');
		$('#students').empty();
		var groupSelect = $('#groups');
		groupSelect.empty();
		var noneGroupHtml = '<option value = "-1">--</option>';
		groupSelect.append(noneGroupHtml);
	}
	
	$('#submit-btn').click(function() {
		var emails = $('#emails').val().trim().split('\n');
		var index = validateEmails(emails);
		if (index >= 0) {
			alert("Wrong email: " + emails[index]);
		} else {
			var path = $(location).attr('pathname');
			var courseId = /courses\/(\d+)\//.exec(path)[1];
			var groupId = $(this).val();
			var url = '/crsms/api/courses/' + courseId + '/groups/' + groupId + '/addstudents';
			emails = removeDuplicates(emails);
			var token = $('#crsf-token').val();
			$.post(url,
					{
						"emails": emails.toString(),
						"_csrf": token
					},
					function(alreadySubscribedUsers) {
						$('#addStudentModal').modal('hide');
						var successAlert = $('#success-alert');
						successAlert.removeClass('hide');
						
						if (alreadySubscribedUsers.length == 0) {
							successAlert.find('#all-subscribed').removeClass('hide');
							successAlert.find('#not-all-subscribed').addClass('hide');
						} else {
							successAlert.find('#all-subscribed').addClass('hide');
							successAlert.find('#not-all-subscribed').removeClass('hide');
							var subscribedUsersCount = emails.length - alreadySubscribedUsers.length;
							successAlert.find('#subscribed-users-count').text(subscribedUsersCount);
							successAlert.find('#not-subscribed-users').text(alreadySubscribedUsers);
						}
					}
			)
		}
	})
	
	//returns index of invalid email
	//returns -1 if all are valid
	function validateEmails(emails) {
		var pattern = /^[a-zA-Z0-9!#$%&'*+\/=?^_`{|}~-]+(\.[a-zA-Z0-9!#$%&'*+\/=?^_`{|}~-]+)*@([a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?\.)+([a-zA-Z]{2,4})$/;
		for (var i = 0; i < emails.length; i++) {
			var email = emails[i].trim();
			if (!pattern.test(email)) {
				return i;
			}
		}
		return -1;
	}
	
	function removeDuplicates(emails) {
		return emails.filter(function(item, pos) {
		    return emails.indexOf(item) == pos;
		})
	}
	
	$('#groups').change(function() {
		var groupId = $('#groups').val();
		var studentsSelect = $('#students');
		
		if (groupId == -1) {
			studentsSelect.empty();
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
	
	$('#students').mousedown(function(e) {
		if (e.target.nodeName == 'SELECT') {
			return false;
		}
	    var option = e.target;
	    if (option.selected) {
	    	option.removeAttribute('selected');
	    	option.selected = false;
	    } else {
	    	option.setAttribute('selected', '');
	    	option.selected = true;
	    }
	    e.preventDefault();
	});
	
	$('#add-btn').click(function() {
		var emails = $('#emails');
		$('#students :selected').each(function(i, selected) {
			emails.val(emails.val() + $(selected).text() + '\n');
		})
	})
	
	$('#add-all-btn').click(function() {
		var emails = $('#emails');
		$('#students option').each(function(i, elem) {
			emails.val(emails.val() + $(elem).text() + '\n');
		})
	})
	
	$('#select-all-btn').click(function() {
		$('#students option').attr("selected", '');
	})
	
	$('#clear-selection-btn').click(function() {
		$('#students option:selected').removeAttr("selected");
	})
})