package org.kilgore.badmovies.request;

import org.kilgore.badmovies.entity.Order;
import org.kilgore.badmovies.response.ProcessOrderResponse;
import org.kilgore.badmovies.util.AppConstants;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component(AppConstants.RB_PROCESS_ORDER)
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
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
