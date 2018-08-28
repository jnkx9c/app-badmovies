package org.kilgore.badmovies.request;

import org.kilgore.badmovies.domain.ShoppingCart;
import org.kilgore.badmovies.response.ShoppingCartPageResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="shoppingcartpage")
@Scope("prototype")
public class ShoppingCartPageRequest extends StorefrontBaseRequest<ShoppingCartPageResponse> {


	
	
	@Override
	protected ShoppingCartPageResponse handleRequest() {
		ShoppingCartPageResponse response = new ShoppingCartPageResponse();
		ShoppingCart shoppingCart = getShoppingCart();

		shoppingCart.calculateTotal();
		response.setShoppingCart(shoppingCart);

		return response;
	}

}
