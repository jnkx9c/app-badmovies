package org.kilgore.badmovies.request;

import javax.servlet.http.HttpSession;

import org.kilgore.badmovies.domain.ShoppingCart;
import org.kilgore.badmovies.response.StorefrontBaseResponse;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class StorefrontBaseRequest<R extends StorefrontBaseResponse> {

	protected R response = null;
	@Autowired
	protected HttpSession httpSession;
	
	protected ShoppingCart shoppingCart;
	

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
	
	public HttpSession getHttpSession() {
		return httpSession;
	}
	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
	
	protected ShoppingCart getShoppingCart() {
		ShoppingCart shoppingCart = null;
		if(httpSession!=null) {
			shoppingCart = (ShoppingCart) httpSession.getAttribute("SHOPPING_CART");
			if(shoppingCart==null) {
				shoppingCart = new ShoppingCart();
				httpSession.setAttribute("SHOPPING_CART", shoppingCart);
			}
		}
		
		
		return shoppingCart;
	}
	
}
