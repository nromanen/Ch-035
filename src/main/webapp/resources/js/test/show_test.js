$(document).ready(function() { 
    $('.indexLink').click(function(thisEl) {
    	$('#nextIndex').val($(this).attr('href'));
        $('#userAnswerForm').submit();
    });
    
    $('#finisButton').click(function() {
    	$("#finish-confirmation-modal").modal('show');
    	return false;
    });
    
    $("#btn-modal-finish-test").click(function() {
    	$('#finished').val("true");
    	$( "#userAnswerForm" ).submit();
    });
});