$(document).ready(function() {
	$('#registration-form').validate({
		errorClass: "errorTxt",	
		rules: {
        	"email": {
        		required: true,
                email: true
            },
            "password": {
            	pattern: '(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}',
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

$.validator.addMethod(
	    "pattern",
	    function(value, element, regexp) {
	        var re = new RegExp(regexp);
	        return this.optional(element) || re.test(value);
	    },
	    "Please check your input."
	);