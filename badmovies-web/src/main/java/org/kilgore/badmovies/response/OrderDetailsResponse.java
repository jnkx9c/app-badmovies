package org.kilgore.badmovies.response;

import org.kilgore.badmovies.entity.Order;

public class OrderDetailsResponse extends StorefrontBaseResponse {

	private Order order;
	
	
	@Override
	public String getView() {
		return "orderdetails";
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}

}
