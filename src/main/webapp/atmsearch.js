$(document).ready(function() {

$('a').click(function(event) {
	   	event.preventDefault();
			var url = $(this).attr('href');
			var resultElement = $('#resultDiv'); 
		    resultElement.html('<br>');
		    
		    $.ajax({
				url : $(this).attr('href'),
				cache : false,
				method : 'GET',
				datatype : 'json',
				success : function(data) {
					var jsonPretty = JSON.stringify(data,undefined,2);
					console.log(jsonPretty);
					$('#resultDiv').append("<pre>" + jsonPretty + "</pre>");
									},
		    	error: function() {
		   		 resultElement.html('<br> Unable to parse incoming JSON data');
		      }
			});

		});


$('#searchbtn').click(function() {
	var url ='/BackbaseTest/rs/listATM/';
	var requestCity = $('#inputsearch').val();
	   var resultElement = $('#resultDiv'); 
	    resultElement.html('<br>');
	if(requestCity == "") {
		
		 resultElement.html('<br> Please enter valid Search City  ');
	}
	var isPresent = false;
 
    requestCity = requestCity.toUpperCase()
	$.ajax({
		url : url+requestCity,
		cache : false,
		method : 'GET',
		datatype : 'json',
		success : function(data) {
			var jsonPretty = JSON.stringify(data,undefined,2);
			console.log(jsonPretty);
			$('#resultDiv').append("<pre>" + jsonPretty + "</pre>");
							},
    	error: function() {
    		$('#resultDiv').append("<br>Invalid Search City.Please enter valid CITY name.</br>");
      }
	});

	
	
});


var $loading = $('#loadingDiv').hide();
$(document)
  .ajaxStart(function () {
    $loading.show();
  })
  .ajaxStop(function () {
    $loading.hide();
  });

});




		