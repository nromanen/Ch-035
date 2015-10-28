$(document).ready(function() {
	$("#changePasswordBtn").click(
		function() {
			var url = "changePassword?currentPass="
					+ $("#currentPass").val() + "&newPassword="
					+ $("#newPassword").val() + "&_csrf="
					+ $("#csrf").val();
			$.post(url).done(function(response) {
				if (response == 'Fail') {
					alert("Different passwords");
				} else {
					$("#closeModalBtn").click();
				}
			}).fail(function() {
				alert("Failed to submit form");
			});
		});
});