    $(document).ready(function() {
	$("#changePasswordBtn").click(
		function() {
			if ($('#changePasswordForm').valid()) {
				var url = "changePassword?currentPass="
						+ $("#currentPass").val() + "&newPassword="
						+ $("#newPassword").val() + "&_csrf="
						+ $("#csrf").val();
				$.post(url).done(function(response) {
					if (response == 'Fail') {
						alert("Enter correct current password");
					} else {
						$("#closeModalBtn").click();
					}
				}).fail(function() {
					alert("Failed to submit form");
				});
			}
		});
	
	var $imageAreaSelect;
	var bufferedCanvas;
	var bufferedContext

	function cropImage(image, x, y, width, height) {
		var canvas = document.getElementById('imageCanvas');
		var context = canvas.getContext('2d');

		canvas.width = width;
		canvas.height = height;

		console.log(canvas.width);
		console.log(canvas.height);
		console.log(x);
		console.log(y);
		var croppedImage = bufferedContext.getImageData(x, y, width, height);

		context.putImageData(croppedImage, 0, 0);

		var imgURL = canvas.toDataURL('image/*');

		return imgURL;
	}

	function submitImage() {
		$("#imagePreview").attr("src", $("#selectedImage").val());
		$("#image").val($("#selectedImage").val());
		$("#imageModal").modal("hide");
	}

	function setupModal() {
		$imageAreaSelect = $("#avatarImage").imgAreaSelect({
            handles: true,
            aspectRatio: '1:1',
            instance: true,
            onSelectEnd: function(image, properties) {
            	$("#selectedImage").val(cropImage(image.getAttribute("src"), properties.x1, properties.y1, properties.width, properties.height));
            }
        });
		$('#imageModal').on('shown.bs.modal', function () {
			$("#avatarImage").attr("src", $('#selectedImage').val());
		});
		$('#imageModal').on('hide.bs.modal', function () {
			$imageAreaSelect.cancelSelection();
		});
	}

    function showAvatarModal() {
        $("#imageInput").click();
    }

    $(document).ready(function() {
    	setupModal();
    	bufferedCanvas = document.getElementById("bufferedCanvas");
		bufferedContext = bufferedCanvas.getContext("2d");

    	$("#showImagePopupBtn").click(showAvatarModal);
    	$("#imageSelectBtn").click(submitImage);
        $("#imageInput").change(function() {
            if (this.files && this.files[0]) {
                var FR = new FileReader();

                FR.onload = function(e) {
                	var img = new Image();

                	 img.onload = function() {
                	 	bufferedContext.drawImage(this, 0, 0, bufferedCanvas.width, bufferedCanvas.height);
                	 }

                    $('#selectedImage').val(e.target.result);
                    $("#imageModal").modal("show");

                    img.src=e.target.result;
                };

                FR.readAsDataURL( this.files[0] );
            }
        });
    });
	
	
	$('#user-information').validate({
        errorClass: "errorTxt",
		rules: {
			"firstName": {
        		regexName: '^[^<>$%~`!@#\\^&*()_+=\\{\\}\\[\\]\\.,|;:"?/\\d\\\\]{1,40}$'
            },
            "lastName": {
        		regexName: '^[^<>$%~`!@#\\^&*()_+=\\{\\}\\[\\]\\.,|;:"?/\\d\\\\]{1,40}$'
            },
        },
    });
	
	$('#changePasswordForm').validate({
        errorClass: "errorTxt",
		rules: {
			"currentPass": {
        		required: true,
        		minlength: 2
            },
            "newPassword": {
            	required: true
            },
        },
    });
});

$.validator.addMethod(
    "regexName",
    function(value, element, regexp) {
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    },
    "Please check your input."
);
