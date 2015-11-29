$(document).ready(function(e) {
	$(function () {
        $('#datetimepicker1').datepicker({
            format: "dd/mm/yyyy",
            language: $.cookie('localeCookie'),
            autoclose: true,
            todayHighlight: true
        });
    });
	
	$('#course-form').validate({
		errorClass: "errorTxt",	
		rules: {
        	"name": {
                required: true,
                minlength: 2,
                maxlength: 255
            },
            "description": {
                required: false
            },
            "duration": {
                required: true,
                min: 1,
                max: 52
            },
        },
    });
});