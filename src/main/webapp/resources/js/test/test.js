$(document).ready(function (e){
	
	function doAjaxPost() {
		var form = $('#modalForm');
		var testId = $('#modal-form-submit').val();

		$.ajax({
			headers: { 
	            'Accept': 'application/json',
	            'Content-Type': 'application/json' 
	        },
			url : document.URL + testId +"/questions/add/json",
			dataType: 'json',
			data : form.serialize(),
			type : "POST",
			success : function(response) {	
				
			},

			error : function(xhr, ajaxOptions, thrownError) {
				alert('Error: ' + thrownError + "\n" + xhr);
			}
		});
	}
	
	$(".question-add").click(function (e){
		 $('#modal-form-submit').val($(this).attr("value"));
	})
	
	$("#modal-form-submit").click(function (e){
		doAjaxPost();
	})
	
})


