function ClearField() {
     document.getElementById("keyWord").value = "";
     document.getElementById("submit").click();
}
function loadPage() {    
    $("#modalEdit").load("/WEB-INF/view/users/adduser.jsp");
    $("#modalEdi").dialog({
        height: 600, 
        width: 600, 
        modal: true
    }); 
    return false;
}