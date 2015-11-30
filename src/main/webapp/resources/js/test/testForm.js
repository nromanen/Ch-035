$(document).ready(function(e) {
	$('#test-form').validate({
		errorClass: "errorTxt",	
		rules: {
        	"name": {
                required: true,
                minlength: 2,
                maxlength: 100
            },
        },
    });
});