$(document).ready(function() {
	
	for (var i = 5; i <= 15; i += 5) {
		$('#limit-select').append("<li" + (getParam('limit') == i ? " class='active'" : "") + "><a href='" + updateURLParameter(window.location.href, 'limit', i) + "'>" + i + "</a></li>");
	}
})

function updateURLParameter(url, param, paramVal){
    var newAdditionalURL = "";
    var tempArray = url.split("?");
    var baseURL = tempArray[0];
    var additionalURL = tempArray[1];
    var temp = "";
    if (additionalURL) {
        tempArray = additionalURL.split("&");
        for (i=0; i<tempArray.length; i++){
            if(tempArray[i].split('=')[0] != param){
                newAdditionalURL += temp + tempArray[i];
                temp = "&";
            }
        }
    }

    var rows_txt = temp + "" + param + "=" + paramVal;
    return baseURL + "?" + newAdditionalURL + rows_txt;
}

function getParam(param) {
    location.search.substr(1)
        .split("&")
        .some(function(item) { 
            return item.split("=")[0] == param && (param = item.split("=")[1])
        })
    return param
}