var langeges = {'ua':'uk'};
$(document).ready(function(e) {
	$(function () {
        $('#datetimepicker1').datepicker({
            format: "dd/mm/yyyy",
            language: langeges[$.cookie('localeCookie')],
            autoclose: true,
            todayHighlight: true
        });
    });
});