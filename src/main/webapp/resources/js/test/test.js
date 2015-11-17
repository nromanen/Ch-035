$(document).ready(function (e){
	
	function doAjaxPost() {
		var form = $('#modal-form');
		var testId = $('#modal-form-submit').val();

		$.ajax({
			url : document.URL + testId +'/questions/add/question-form',
			dataType: 'json',
			data : form.serialize(),
			type : "POST",
			
			complete: function() {
				//Hide add question modal after form submitting.
				$("#my-modal").modal('hide');
				
				//Clear text area after add question form closing.
				$(".clear-textarea").val("");
					
			} ,
			success : function(question) {
				var questionHtml =
				'<div id="info">' +
					'<ul class="list-group" >' +
						'<li class="list-group-item list-group-item-warning">' +
						'<input type="hidden" value="' + question.id + '">' +
							'<a href="" class="list-group-item-warning">' +
								question.text +
								'&nbsp' +
								'<a href="#" class="nonUnderlineDelete pull-right"><i class="glyphicon glyphicon-trash"></i></a>' +
								'<a href="#" class="nonUnderlineEdit pull-right"><i class="glyphicon glyphicon-pencil"></i>&nbsp</a>' +								
							'</a>' +
						'</li>' +
					'</ul>' +
				'</div>';
				
				$("#questions-" + testId).append(questionHtml);
		
			},

			error : function(xhr, ajaxOptions, thrownError) {
				alert('Error: ' + thrownError + "\n" + xhr);
			}
		});
	}
	
	//Only one Test div can be shown in the moment.
	$('div.full-div').click(function(e){
		$('.collapse-off').collapse('hide');
	});
	
	//"Close" button. Hide collapse area.
	$('.close-div-button').click(function(e){
		$('.collapse-off').collapse('hide');
	});
	
	//Get Test id from inner JSP for-each loop.
	$(".question-add").click(function (e){
		 $('#modal-form-submit').val($(this).attr("value"));
	});
	
	//Send ajax request when form submitted.
	$("#modal-form-submit").click(function (e){
		doAjaxPost();
	});
	
})


