$(document).ready(function(e) {
	$(function () {
        $('#datetimepicker1').datepicker({
            format: "dd/mm/yyyy",
            language: $.cookie('localeCookie'),
            autoclose: true,
            todayHighlight: true
        });
    });
	
	$('#group-form').validate({
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