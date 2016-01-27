$(document).ready(function(e) {
	$('#course-form').validate({
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
            "duration": {
                required: true,
                number: true,
                min: 1,
                max: 52
            },
        },
    });
});