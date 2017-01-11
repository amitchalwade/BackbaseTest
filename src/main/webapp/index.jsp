<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BackbaseTest</title>
<style>
.button {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
}

.loader {
  border: 16px solid #f3f3f3;
  border-radius: 50%;
  border-top: 16px solid #3498db;
  width: 100px;
  height: 100px;
  -webkit-animation: spin 2s linear infinite;
  animation: spin 2s linear infinite;
   display: none;
  
}

@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="atmsearch.js">
	
</script>
</head>
<body>
	<div class="row">
		<h2>Backbase Test</h2>
	</div>


<div class="row">
		
	Search ATM City: <input type="text" name="citysearch" id="inputsearch">  <input id ="searchbtn" type="button" class="button" value="Search">
	
	</div>
	<div class="row">
	<ul>
		<li><a href="/BackbaseTest/rs/listATM/ING" id="listATM">(Default ATM:TYPE )-ING Search</a>
			- REST API access to extract ATM list with type (ING) using  Camel - RESTLET servlet.</li>
		</ul>
</div>
	<h2>List of all matching search city result will be listed below </h2>
	<div id="resultDiv"></div>

	<br></br>
	
		<div class="loader" id="loadingDiv"></div>
</body>
</html>
