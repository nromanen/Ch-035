$(document).ready(function(e) {
	
	// tooltips
	$('[data-toggle="tooltip"]').tooltip();
	
	// scrolling effects
	var offset = 250; // At what pixels start events
    $(window).scroll(function() {
    	// scroll to top button fade
    	if ($(this).scrollTop() > offset) {
    		$('#scroll-to-top').fadeIn(500); // Time(in Milliseconds) of appearing of the Button when scrolling down.
        } else {
        	$('#scroll-to-top').fadeOut(500); // Time(in Milliseconds) of disappearing of Button when scrolling up.
        }
    });   
    // scroll to top button click animation
    $("#scroll-to-top").click(function(e) {
    	$('#logo').animatescroll({scrollSpeed:700,easing:'easeOutCirc'});
    });
});