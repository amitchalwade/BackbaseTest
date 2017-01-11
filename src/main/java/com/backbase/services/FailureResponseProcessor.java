package com.backbase.services;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Status;

/**
 * 
 * @author amit
 *  Error handler in case of any exception in Camel Route
 */
public class FailureResponseProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
		response.setStatus(Status.SERVER_ERROR_INTERNAL);
		response.setEntity("<h1>Unable to resolve to url, internal server error</h1>", MediaType.TEXT_HTML);
		exchange.getOut().setBody(response);
		
	}

}
