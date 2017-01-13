# BackbaseTest
Camel Restlet

Platform support · Jetty or Tomcat 7 or 8 · JDK 8

Compile: mvn clean install

Run the application: mvn jetty:run

Open web browser with http://localhost:8080/BackbaseTest/

To retrieve an ATM list data , send search with valid CITY name in input text box. REST service url - http://localhost:8080/BackbaseTest/rs/listATM/{cityname}

AJAX call made from index.jsp will get JSON data object back fron REST web service and display on page.

Please use username-admin and password - admin for authetication 

