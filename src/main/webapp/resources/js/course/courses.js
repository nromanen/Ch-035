function ClearField() {
     document.getElementById("search").value = "";
     document.getElementById("submit").click();
}

$.validator.addMethod(
    "regexSearch",
    function(value, element, regexp) {
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    },
    "Please check your input."
);

$(document).ready(function() {
	$('#searchForm').validate({
		rules: {
        	"searchWord": {
        		regexSearch: "^[^<>$%]{1,40}$",
            },
        },
        errorElement : 'div',
        errorLabelContainer: '.errorTxt'
    });
});