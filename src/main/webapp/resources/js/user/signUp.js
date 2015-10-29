$(document).ready(function(e) {
		$("#registration-form").submit(function(e) {
		var pass = document.getElementById("password").value;
		var confPass = document.getElementById("confPassword").value;
		if (pass != confPass) {
			alert("Passwords should be identical");
			e.preventDefault();
		}
	});

});