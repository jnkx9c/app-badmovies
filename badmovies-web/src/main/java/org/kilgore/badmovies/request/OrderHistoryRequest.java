package org.kilgore.badmovies.request;

import java.util.List;

import org.kilgore.badmovies.entity.Order;
import org.kilgore.badmovies.response.OrderHistoryResponse;
import org.kilgore.badmovies.util.AppConstants;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(AppConstants.RB_ORDER_HISTORY)
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OrderHistoryRequest extends StorefrontBaseRequest<OrderHistoryResponse>{

	@Override
	protected OrderHistoryResponse handleRequest() {

		OrderHistoryResponse response = new OrderHistoryResponse();
		List<Order> orders = storeFrontService.findOrders(user);
		response.setOrders(orders);
		return response;
	}

	
	
	
	
}
