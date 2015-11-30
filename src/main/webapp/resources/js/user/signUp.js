$(document).ready(function() {
	$('#registration-form').validate({
		errorClass: "errorTxt",	
		rules: {
        	"email": {
        		required: true,
                email: true
            },
            "password": {
            	minlength: 6,
            	maxlength: 255
            },
            "confPassword": {
            	equalTo: "#password",
            	minlength: 6,
            	maxlength: 255
            },
        },
    });
});

$(document).ready(function(){
    $('[data-toggle="popover"]').popover();   
});