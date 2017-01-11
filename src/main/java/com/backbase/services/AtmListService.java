package com.backbase.services;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.restlet.RestletConstants;
import com.backbase.bean.AtmObject;
import com.backbase.services.*;
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

/**
 * 
 * @author amit chalwade
 * AtmListService is a base class which is derived from to create routing rules using the DSL.
 *  Call  to REST API and publish response back.
 */
public class AtmListService extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		onException(Exception.class).handled(true).process(new FailureResponseProcessor());
//
		from("restlet:/listATM/{city}?restletMethod=GET&restletRealm=#realm")
				//.setHeader("Content-Type", constant("application/json"))

				.process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						// the Restlet request should be available if needed

						// Request request =
						// exchange.getIn().getHeader(RestletConstants.RESTLET_REQUEST,
						// Request.class);

						String uri = exchange.getIn().getHeader(Exchange.HTTP_URI, String.class);

						Client client = new Client(Protocol.HTTPS);

						// The URI of the resource "list of items".
						Reference samplesUri = new Reference("https://www.ing.nl/api/locator/atms/");

						Request request1 = new Request(Method.GET, samplesUri);

						Response response = client.handle(request1);

						Representation result = response.getEntity();

//						try { 
//						AtmObject atm = new ObjectMapper().readValue(result.getText(),
//						 AtmObject.class);
//
//						System.out.println(atm.getAddress().getCity());
//						
//						} catch (Exception e) {
//							e.printStackTrace();
//
//							throw new Exception();
//						}
						StringWriter sw = new StringWriter();

						if (result != null) {
							result.write(sw);
						}
						String responseStr = sw.toString();
						StringBuilder jsonData = new StringBuilder();
						 String json = responseStr.substring(7,
						 responseStr.length()-1);
						 jsonData.append("{\"ATM\":[").append(json).append("]}");
						
						 
//						System.out.println("responseStr" + responseStr);

						// use Restlet API to create the response
						Response response1 = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE,
								Response.class);
						response1.setStatus(Status.SUCCESS_OK);

						response1.setEntity(jsonData.toString(), MediaType.TEXT_HTML);
						exchange.getOut().setBody(response1);

						
					}
				});

	}



}
