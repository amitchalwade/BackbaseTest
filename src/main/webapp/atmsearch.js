$(document).ready(function() {

$('a').click(function(event) {
			event.preventDefault();
			var url = $(this).attr('href');
			var resultElement = $('#resultDiv'); 
		    resultElement.html('<br>');
		    
		      
			$.ajax({
				url : $(this).attr('href'),
				cache : false,
				method : 'get',
				datatype : 'json',
				success : function(data) {
				
					var arr = $.parseJSON(data);

					$.each(arr, function(index, value) {

						$.each(value, function(key, innervalue) {
							
							if(innervalue.type == "ING") {
								$("#resultDiv").append("<ul><li>TYPE   : "+ innervalue.type+"</li></ul>" );
								$("#resultDiv").append("<li>Address- City : " +innervalue.address.city+"</li>" );
								$("#resultDiv").append("<li>House Number : "+ innervalue.address.housenumber+"</li>" );
								$("#resultDiv").append("<li>Postal Code  : "+ innervalue.address.postalcode+"</li>" );
								$("#resultDiv").append("<li>Street : "+ innervalue.address.street+"</li>" );
								$("#resultDiv").append("<li>Geolocation- Lattitude : "+ innervalue.address.geoLocation.lat+"</li>" );
								$("#resultDiv").append("<li>Geolocation- Londutue  : "+ innervalue.address.geoLocation.lng+"</li>" );
								$("#resultDiv").append("<li>Distance to ATM  : "+ innervalue.distance+"</li>" );
								$("#resultDiv").append("----------------------------------------------------------------------------" );
							}
							

						});

					});
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
		method : 'get',
		datatype : 'json',
		success : function(data) {
			
			var arr = $.parseJSON(data);
			$.each(arr, function(index, value) {

				$.each(value, function(key, innervalue) {
					
					if(innervalue.address.city == requestCity ) {
						isPresent = true;
						$("#resultDiv").append("<ul><li>Address- City : " +innervalue.address.city+"</li>" );
						$("#resultDiv").append("<li>House Number : "+ innervalue.address.housenumber+"</li>" );
						$("#resultDiv").append("<li>Postal Code  : "+ innervalue.address.postalcode+"</li>" );
						$("#resultDiv").append("<li>Street : "+ innervalue.address.street+"</li>" );
						$("#resultDiv").append("<li>Geolocation- Lattitude : "+ innervalue.address.geoLocation.lat+"</li>" );
						$("#resultDiv").append("<li>Geolocation- Londutue  : "+ innervalue.address.geoLocation.lng+"</li>" );
						$("#resultDiv").append("<li>Distance to ATM  : "+ innervalue.distance+"</li>" );
						$("#resultDiv").append("<li>TYPE   : "+ innervalue.type+"</li></ul>" );
						$("#resultDiv").append("----------------------------------------------------------------------------" );
						
					
					}
					

				});

			});
		if(isPresent == false) {
				
				 resultElement.html('<br> Invalid Search City - No data found ');
			}
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




		