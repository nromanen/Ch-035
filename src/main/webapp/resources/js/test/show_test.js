$(document).ready(function() { 
    $('.indexLink').click(function(thisEl) {
    	$('#nextIndex').val($(this).attr('href'));
        $('#duration').val(timer);
        $('#userAnswerForm').submit();
        return false;
    });
    
    $('#finisButton').click(function() {
    	$("#finish-confirmation-modal").modal('show');
    	return false;
    });
    
    $("#btn-modal-finish-test").click(function() {
    	$('#finished').val("true");
    	$( "#userAnswerForm" ).submit();
    });

    function startTimer(duration, display) {
        timer = duration;
        var minutes, seconds;
        setInterval(function () {
            minutes = parseInt(timer / 60, 10)
            seconds = parseInt(timer % 60, 10);

            minutes = minutes < 10 ? "0" + minutes : minutes;
            seconds = seconds < 10 ? "0" + seconds : seconds;

            display.text(minutes + ":" + seconds);

            if (--timer < 0) {
                $('#finished').val("true");
                $( "#userAnswerForm" ).submit();
            }
        }, 1000);
    }

    function initializeTimer() {
        var display = $('#timer');
        if (duration != 0)
            startTimer(timer, display);
    }

    var timer = +$('#timer').attr("data-duration");
    initializeTimer();
});