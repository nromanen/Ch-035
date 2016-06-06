$(document).ready(function (e) {

    $(".clear-score-button").click(function (e) {
        var clearScoreUrl = $(this).attr("data-clearscoreurl");

        $("#btn-modal-clear-score").attr("data-clearscoreurl", clearScoreUrl);

        $("#clear-score-confirmation-modal").modal('show');
    });


    $("#btn-modal-clear-score").click(function (e) {
        var url = document.URL + '/' + $(this).attr("data-clearscoreurl");

        $.ajax({
            url : url,
            dataType: 'text',
            data : $("#clear-score-form").serialize(),
            type : "POST",

            complete: function() {
                $("#clear-score-confirmation-modal").modal('hide');
            },

            error : function(xhr, ajaxOptions, thrownError) {
                alert('Error: ' + thrownError + "\n" + xhr);
            }
        });
    });
});
