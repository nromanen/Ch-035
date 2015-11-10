$(document).ready(function (e){
	
	function doAjaxPost() {
		var form = $('#modalForm');
		var testId = $('#modal-form-submit').val();

		$.ajax({
			url : document.URL + testId +'/questions/add/json',
			dataType: 'json',
			data : form.serialize(),
			type : "POST",
			beforeSend: function() {
				
			},
			complete: function() {
				$("#my-modal").modal('hide');
				$("#hover-" + testId).click();
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
	
	$(".question-add").click(function (e){
		 $('#modal-form-submit').val($(this).attr("value"));
	})
	
	$("#modal-form-submit").click(function (e){
		doAjaxPost();
	})
	
})


