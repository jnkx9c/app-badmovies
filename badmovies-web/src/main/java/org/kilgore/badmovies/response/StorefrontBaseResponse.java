package org.kilgore.badmovies.response;

import org.kilgore.badmovies.domain.ShoppingCart;

public abstract class StorefrontBaseResponse {

	
	private ShoppingCart shoppingCart;
	
	
	
	public abstract String getView();

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	
}
