$(document).ready(function() { 
    $('.indexLink').click(function(thisEl) {
    	$('#nextIndex').val($(this).attr('href'));
        $('#userAnswerForm').submit();
    });
});