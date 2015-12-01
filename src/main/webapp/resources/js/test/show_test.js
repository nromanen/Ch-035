$(document).ready(function() { 
    $('.indexLink').click(function(thisEl) {
    	$('#nextIndex').val($(this).attr('href'));
        $('#userAnswerForm').submit();
    });
    
    $('#finisButton').click(function() {
    	$('#finished').val("true");
    });
});