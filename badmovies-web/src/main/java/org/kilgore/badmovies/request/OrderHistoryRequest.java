package org.kilgore.badmovies.request;

import org.kilgore.badmovies.response.OrderHistoryResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("orderhistory")
@Scope("prototype")
public class OrderHistoryRequest extends StorefrontBaseRequest<OrderHistoryResponse>{

	@Override
	protected OrderHistoryResponse handleRequest() {

		
		
		OrderHistoryResponse response = new OrderHistoryResponse();
		return response;
	}

	
	
	
	
}
