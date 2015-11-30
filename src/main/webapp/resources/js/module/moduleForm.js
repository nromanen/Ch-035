$(document).ready(function(e) {
	$('#module-form').validate({
		errorClass: "errorTxt",	
		rules: {
        	"name": {
                required: true,
                minlength: 2,
                maxlength: 255
            },
            "description": {
                required: true
            },
        },
    });
});