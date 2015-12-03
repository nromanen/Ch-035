$(document).ready(function() {
	$('#registration-form').validate({
		errorClass: "errorTxt",	
		rules: {
        	"email": {
        		required: true,
        		regexEmail: '^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*\\.[A-Za-z]{2,}$'
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

$.validator.addMethod(
	    "regexEmail",
	    function(value, element, regexp) {
	        var re = new RegExp(regexp);
	        return this.optional(element) || re.test(value);
	    },
	    "Please check your input."
	);