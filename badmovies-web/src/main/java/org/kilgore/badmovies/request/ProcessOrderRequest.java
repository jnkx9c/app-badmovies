package org.kilgore.badmovies.request;

import org.kilgore.badmovies.response.ProcessOrderResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("processorder")
@Scope("prototype")
public class ProcessOrderRequest extends StorefrontBaseRequest<ProcessOrderResponse>{

	@Override
	protected ProcessOrderResponse handleRequest() {
		ProcessOrderResponse response = new ProcessOrderResponse();
		
		
		
		//finally, clear the shopping cart
		getShoppingCart().clear();
		
		return response;
		
	}

}
