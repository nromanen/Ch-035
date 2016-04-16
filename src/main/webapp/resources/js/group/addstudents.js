$(document).ready(function() {
	/*
	 * By email
	 */
	$('#by-email-tab').on('shown.bs.tab', function (e) {
		$('#emails').focus();
	})
	
	$('#submit-by-email-btn').click(function() {
		var emails = $('#emails').val().trim().split(/[\n\s]+/);
		if (emails[0] == "") {
			showNoEmailsChosenAlert();
			return;
		}
		var index = validateEmails(emails);
		if (index >= 0) {
			showWrongEmailAlert(emails[index]);
		} else {
			addToGroup(removeDuplicates(emails));
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
	
	/*
	 * From other group
	 */
	$('#from-other-group-tab').on('shown.bs.tab', function (e) {
		resetFields();
		
		var groupSelect = $('#groups');
		groupSelect.focus();
		var url = '/crsms/api/groups/';
		var path = $(location).attr('pathname');
		var currentGroupId = /groups\/(\d+)\//.exec(path)[1];
		
		$.get(url, function(groups) {
			for (var i = 0; i < groups.length; i++) {
				if (groups[i].id == currentGroupId) {
					continue;
				}
				var groupOptionHtml = '<option value="' + groups[i].id + '">' + groups[i].name + '</option>'
				groupSelect.append(groupOptionHtml);
			}
		})
	})
	
	function resetFields() {
		var defaultOption = $('#groups option:first-child');
		$('#students-from-other-group').empty();
		$('#groups').empty();
		$('#groups').append('<option value = "-1">--</select>');
	}
	
	$('#groups').change(function() {
		var groupId = $('#groups').val();
		var studentsSelect = $('#students-from-other-group');
		
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
	
	$('#select-all-btn').click(function() {
		$('#students-from-other-group option').attr("selected", '');
	})
	
	$('#clear-selection-btn').click(function() {
		$('#students-from-other-group option:selected').removeAttr("selected");
	})
	
	$('#submit-from-other-group-btn').click(function() {
		makeArrayAndSubmit($('#students-from-other-group :selected'));
	})
	
	/*
	 * Search
	 */
	$('#search-tab').on('shown.bs.tab', function (e) {
		$('#clear-search').click();
	})
	
	$('#clear-search').click(function() {
		$('#students-from-search').empty();
	})
	
	$('#search-btn').click(function() {
		var token = $('#crsf-token').val();
		var textToSearch = $('#search').val();
		var url = '/crsms/api/students/search';
		
		$.post(url,
				{
					"textToSearch": textToSearch,
					"_csrf": token
				},
				function(students) {
					var studentsSelect = $('#students-from-search');
					studentsSelect.empty();
					for (var i = 0; i < students.length; i++) {
						var studentOptionHtml = '<option value = "' + students[i].id + '">'
						+ students[i].firstName + ' ' + students[i].lastName + ' ' + students[i].email
						+ '</option>';
						studentsSelect.append(studentOptionHtml);
					}
				}
			)
	})
	
	$('#submit-search-btn').click(function() {
		makeArrayAndSubmit($('#students-from-search :selected'));
	})
	
	/*
	 * Common
	 */
	function makeArrayAndSubmit(optionEmails) {
		var emails = [];
		$(optionEmails).each(function(i, studentEmail) {
			emails.push(/(\w+@\w+.\w+)/.exec($(studentEmail).text())[1]);
		})
		addToGroup(emails);
	}
	
	function addToGroup(emails) {
		if (emails.length == 0) {
			showNoEmailsChosenAlert();
			return;
		}
		var path = $(location).attr('pathname');
		var courseId = /courses\/(\d+)\//.exec(path)[1];
		var groupId = /groups\/(\d+)\//.exec(path)[1];
		var url = $('#api-url').val() + '/courses/' + courseId + '/groups/' + groupId + '/addstudents';
		var token = $('#crsf-token').val();
		$.post(url,
			{
				"emails": emails.toString(),
				"_csrf": token
			},
			function(alreadySubscribedUsers) {
				showSuccessAlert(emails, alreadySubscribedUsers);
			}
		)
	}
	
	function showSuccessAlert(emails, alreadySubscribedUsers) {
		var alert = $('#alert');
		alert.show();
		
		if (alreadySubscribedUsers.length == 0) {
			showAllSubscribedAlert(alert);
		} else {
			showNotAllSubscribedAlert(alert, emails, alreadySubscribedUsers);
		}
	}
	
	function showAllSubscribedAlert(alert) {
		alert.find('#all-subscribed').removeClass('hide');
		alert.find('#not-all-subscribed').addClass('hide');
		alert.find('#no-email-chosen').addClass('hide');
		alert.find('#wrong-email-alert').addClass('hide');
	}
	
	function showNotAllSubscribedAlert(alert, emails, alreadySubscribedUsers) {
		alert.find('#not-all-subscribed').removeClass('hide');
		alert.find('#all-subscribed').addClass('hide');
		alert.find('#no-email-chosen').addClass('hide');
		alert.find('#wrong-email-alert').addClass('hide');
		var subscribedUsersCount = emails.length - alreadySubscribedUsers.length;
		alert.find('#subscribed-users-count').text(subscribedUsersCount);
		alert.find('#not-subscribed-users').text(alreadySubscribedUsers);
	}
	
	function showNoEmailsChosenAlert() {
		var alert = $('#alert');
		alert.show();
		
		alert.find('#no-email-chosen').removeClass('hide');
		alert.find('#all-subscribed').addClass('hide');
		alert.find('#not-all-subscribed').addClass('hide');
		alert.find('#wrong-email-alert').addClass('hide');
	}
	
	function showWrongEmailAlert(email) {
		var alert = $('#alert');
		alert.show();

		alert.find('#wrong-email-alert').removeClass('hide');
		$('#wrong-email').text(email);
		
		alert.find('#no-email-chosen').addClass('hide');
		alert.find('#all-subscribed').addClass('hide');
		alert.find('#not-all-subscribed').addClass('hide');
	}

	$('#close-alert-btn').click(function() {
		$('#alert').hide();
	})
})

	