package org.kilgore.badmovies.response;

import java.util.List;

import org.kilgore.badmovies.entity.Movie;

public class ShoppingCartPageResponse extends StorefrontBaseResponse{

	private List<Movie> shoppingCartMovies;

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



}
