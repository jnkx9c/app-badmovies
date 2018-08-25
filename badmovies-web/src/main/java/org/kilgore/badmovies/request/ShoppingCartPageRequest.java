package org.kilgore.badmovies.request;

import java.util.List;

import org.kilgore.badmovies.domain.ShoppingCart;
import org.kilgore.badmovies.entity.Movie;
import org.kilgore.badmovies.response.ShoppingCartPageResponse;
import org.kilgore.badmovies.service.StoreFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="shoppingcartpage")
@Scope("prototype")
public class ShoppingCartPageRequest extends StorefrontBaseRequest<ShoppingCartPageResponse> {

	@Autowired
	private StoreFrontService storeFrontService;
	
	
	@Override
	protected ShoppingCartPageResponse handleRequest() {
		ShoppingCartPageResponse response = new ShoppingCartPageResponse();
		ShoppingCart shoppingCart = getShoppingCart();
		List<Movie> movies = storeFrontService.findMoviesById(shoppingCart.getMovieIds()); 
		response.setShoppingCartMovies(movies);
		float shoppingCartPrice = 0.0f;
		for(Movie movie: movies) {
			if(movie!=null && movie.getPrice()!=null) {
				shoppingCartPrice+=movie.getPrice().floatValue();
			}
		}
		response.setShoppingCartTotalPrice(shoppingCartPrice);
		return response;
	}

}
