package org.kilgore.badmovies.request;

import java.util.Map;

import org.kilgore.badmovies.domain.ShoppingCart;
import org.kilgore.badmovies.response.StorefrontBaseResponse;
import org.kilgore.badmovies.service.StoreFrontService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class StorefrontBaseRequest<R extends StorefrontBaseResponse> {

	protected R response = null;

	
	@Autowired
	protected StoreFrontService storeFrontService;
	
	protected ShoppingCart shoppingCart;
	protected Map<String, String> parameters = null;
	

	private void preprocess() {
		shoppingCart = getShoppingCart();
	}
	private void postprocess() {
		if(response!=null) {
			response.setShoppingCart(shoppingCart);
		}
	}
	
	protected abstract R handleRequest();
	
	public R execute() {
		preprocess();
		response = handleRequest();
		postprocess();
		
		return response;
	}
	

	
	protected ShoppingCart getShoppingCart() {
		return storeFrontService.getShoppingCart();
	}
	
	public Map<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
	
}
