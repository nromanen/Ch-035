$(document).ready(function() {
	$('#registration-form').validate({
		errorClass: "errorTxt",	
		rules: {
        	"email": {
        		required: true,
                email: true
            },
            "confPassword": {
            	equalTo: "#password"
            },
        },
    });
});