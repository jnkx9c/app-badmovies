package org.kilgore.badmovies.request;

import org.kilgore.badmovies.entity.Order;
import org.kilgore.badmovies.entity.User;
import org.kilgore.badmovies.response.ProcessOrderResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("processorder")
@Scope("prototype")
public class ProcessOrderRequest extends StorefrontBaseRequest<ProcessOrderResponse>{

	@Override
	protected ProcessOrderResponse handleRequest() {
		ProcessOrderResponse response = new ProcessOrderResponse();
		Order order = storeFrontService.createOrder(user,getShoppingCart());
		response.setProcessedOrder(order);
		
		//finally, clear the shopping cart
		getShoppingCart().clear();
		
		return response;
		
	}

}
