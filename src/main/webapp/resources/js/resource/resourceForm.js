$(document).ready(function(e) {	
	$('#embedded-form').validate({
		errorClass: "errorTxt",	
		rules: {
        	"name": {
                required: true,
                minlength: 1,
                maxlength: 100
            },
            "path": {
            	required: true,
            	url: true,
            	maxlength: 255
            },
        },
    });
	
	$('#file-form').validate({
		errorClass: "errorTxt",	
		rules: {
        	"file": {
                required: true
            },
        },
    });
});