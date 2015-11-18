$(document).ready(function() {
	$("#changePasswordBtn").click(
		function() {
			var url = "changePassword?currentPass="
					+ $("#currentPass").val() + "&newPassword="
					+ $("#newPassword").val() + "&_csrf="
					+ $("#csrf").val();
			$.post(url).done(function(response) {
				if (response == 'Fail') {
					alert("Enter correct current password");
				} else {
					$("#closeModalBtn").click();
				}
			}).fail(function() {
				alert("Failed to submit form");
			});
		});
	
	$("#pic").change(function() {
		if (this.files && this.files[0]) {
	        var reader = new FileReader();

	        reader.onload = function (e) {
	        	var base64Image = e.target.result;
	            $('#imagePreview').attr('src', base64Image)
	            $("#image").attr('value', base64Image);
	        }

	        reader.readAsDataURL(this.files[0]);
	    }
	});
});
