package com.backbase.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.restlet.RestletConstants;

import com.backbase.bean.ATM;
import com.backbase.bean.ParseJSON;
import com.backbase.bean.RootObject;
import com.backbase.services.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.restlet.Client;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.data.Reference;
import org.restlet.data.Status;
import org.restlet.engine.header.StringWriter;
import org.restlet.representation.Representation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * 
 * @author amit chalwade AtmListService is a base class which is derived from to
 *         create routing rules using the DSL. Call to REST API and publish
 *         response back.
 */
public class AtmListService extends RouteBuilder {
	
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		onException(Exception.class).handled(true).process(new FailureResponseProcessor());
		//
		from("restlet:/listATM/{city}?restletMethod=GET&restletRealm=#realm")
				.setHeader("Content-Type", constant("application/json"))

				.process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						// the Restlet request should be available if needed

						
						String uri = exchange.getIn().getHeader(Exchange.HTTP_URI, String.class);
						String city = null;
					
						if(uri.contains("?")) {
							 city = uri.split("listATM/")[1].split("[?]")[0];
						
						 }else { 
							 city = uri.split("listATM/")[1];
							 
						 }
						 						
						Client client = new Client(Protocol.HTTPS);

						// The URI of the resource "list of items".
						Reference inputURL = new Reference("https://www.ing.nl/api/locator/atms/");

						Response response = client.handle(new Request(Method.GET, inputURL));

						Representation result = response.getEntity();

						StringWriter sw = new StringWriter();

						if (result != null) {
							result.write(sw);
						}
						String responseStr = sw.toString();
						StringBuilder jsonData = new StringBuilder();
						String json = responseStr.substring(7, responseStr.length() - 1);
						jsonData.append("{\"ATM\":[").append(json).append("]}");

					//	System.out.println("Search String is " + city);
						
						String prettyJSON = getParsedJSON(jsonData.toString(), city);

						if (prettyJSON != null) {
							// use Restlet API to create the response
							Response response1 = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE,
									Response.class);
							response1.setStatus(Status.SUCCESS_OK);
							response1.setEntity(prettyJSON, MediaType.APPLICATION_ALL_JSON);
							exchange.getOut().setBody(response1);

						} else {
							throw new Exception();
						}

					}
				});

	}

	/**
	 *  Search JSON for given input CITY . 
	 * @param inputJson
	 * @param city
	 * @return
	 */
	private static String getParsedJSON(String inputJson, String city) {

		StringBuilder prettyjson = new StringBuilder();
		prettyjson.append("{\"ATM\":[");
		try {

			ObjectMapper objectMapper = new ObjectMapper();

			RootObject root = objectMapper.readValue(inputJson, RootObject.class);

			List<ATM> atmList = root.getATM();
			List<ATM> searchMatchList = new ArrayList<>();

			for (Iterator iterator = atmList.iterator(); iterator.hasNext();) {
				ATM atm = (ATM) iterator.next();
                     
				if(city!=null && city.equals("ING") && atm.getType().equals(city.toUpperCase())) { 
					searchMatchList.add(atm);
				}
				
				if ( atm.getAddress().getCity().equals(city.toUpperCase())) {

					searchMatchList.add(atm);
				}

			}

			if (searchMatchList != null && searchMatchList.size() > 0) {

				int counter = 0 ;
				for (Iterator iterator = searchMatchList.iterator(); iterator.hasNext();) {
					ATM atm = (ATM) iterator.next();
					counter++;

					// Pretty print
					prettyjson.append(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(atm));

					if(counter <searchMatchList.size()) {
						prettyjson.append(",");
					}

				}

			}

		//	System.out.println(prettyjson.toString());

		} catch (Exception e) {
			
			return null;

		}
		return prettyjson.append("]}").toString();

	}// End of method 

}
