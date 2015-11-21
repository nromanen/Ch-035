$(document).ready(function() {
	$('#saveArea').validate({
		rules: {
        	"name": {
                required: true,
                minlength: 2,
                maxlength: 100
            },
        },
        
        errorElement : 'div',
        errorLabelContainer: '.errorTxt'
    });
});