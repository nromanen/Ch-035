$(document).ready(function() {
	$('#saveArea').validate({
        errorClass: "error",
		rules: {
        	"name": {
                required: true,
                minlength: 2,
                maxlength: 100
            },
        },
    });
});