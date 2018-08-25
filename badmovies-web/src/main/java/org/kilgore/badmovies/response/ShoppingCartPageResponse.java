package org.kilgore.badmovies.response;

import java.util.List;

import org.kilgore.badmovies.entity.Movie;

public class ShoppingCartPageResponse extends StorefrontBaseResponse{

	private List<Movie> shoppingCartMovies;
	private float shoppingCartTotalPrice = 0.0f;
	
	
	public List<Movie> getShoppingCartMovies() {
		return shoppingCartMovies;
	}



	public void setShoppingCartMovies(List<Movie> shoppingCartMovies) {
		this.shoppingCartMovies = shoppingCartMovies;
	}



	@Override
	public String getView() {
		return "shoppingcart";
	}



	public float getShoppingCartTotalPrice() {
		return shoppingCartTotalPrice;
	}



	public void setShoppingCartTotalPrice(float shoppingCartTotalPrice) {
		this.shoppingCartTotalPrice = shoppingCartTotalPrice;
	}

}
