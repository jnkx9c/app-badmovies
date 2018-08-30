package org.kilgore.badmovies.response;

import java.util.List;

import org.kilgore.badmovies.entity.Order;

public class OrderHistoryResponse extends StorefrontBaseResponse{

	private List<Order> orders;
	
	
	@Override
	public String getView() {
		return "orderhistory";
	}


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
