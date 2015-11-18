function ClearField() {
     document.getElementById("search").value = "";
}

$.validator.addMethod(
        "regex",
        function(value, element, regexp) {
            var re = new RegExp(regexp);
            return this.optional(element) || re.test(value);
        },
        "Please check your input."
);

$(document).ready(function() {
	$('#searchForm').validate({
        errorClass: "error",
		rules: {
        	"searchWord": {
        		regex: "^[\\w\\s#-\\.]{1,40}$",
            },
        },
        messages: {},
        errorElement : 'div',
        errorLabelContainer: '.errorTxt'
    });
});