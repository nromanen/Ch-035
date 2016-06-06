$(document).ready(function (e) {

    $(".start-test-btn").click(function (e) {
        var url = $(this).attr("data-url");
        if (url != "") {
            $("#btn-modal-start-test").attr("href", url);

            $("#start-test-confirmation-modal").modal('show');
        }
    });
});
