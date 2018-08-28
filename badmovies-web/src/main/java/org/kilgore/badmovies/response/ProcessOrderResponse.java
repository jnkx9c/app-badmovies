package org.kilgore.badmovies.response;

import org.kilgore.badmovies.entity.Order;

public class ProcessOrderResponse extends StorefrontBaseResponse{

	private Order processedOrder;
	
	
	@Override
	public String getView() {
		return "orderconfirmation";
	}


	public Order getProcessedOrder() {
		return processedOrder;
	}


	public void setProcessedOrder(Order processedOrder) {
		this.processedOrder = processedOrder;
	}

}
