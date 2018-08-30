package org.kilgore.badmovies.request;

import org.kilgore.badmovies.entity.Order;
import org.kilgore.badmovies.response.OrderDetailsResponse;
import org.kilgore.badmovies.util.AppConstants;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(AppConstants.RB_ORDER_DETAILS)
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OrderDetailsRequest extends StorefrontBaseRequest<OrderDetailsResponse>{

	@Override
	protected OrderDetailsResponse handleRequest() {
		OrderDetailsResponse response = new OrderDetailsResponse();
		Integer orderId = (Integer) getParameters().get(AppConstants.PARAM_ORDER_ID);
		Order order = storeFrontService.findOrder(getUser(),orderId );
		response.setOrder(order);
		return response;		
	}

}
