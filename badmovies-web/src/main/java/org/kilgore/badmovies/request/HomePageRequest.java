package org.kilgore.badmovies.request;

import java.util.List;

import org.kilgore.badmovies.entity.Movie;
import org.kilgore.badmovies.response.HomePageResponse;
import org.kilgore.badmovies.service.StoreFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="home")
@Scope("prototype")
public class HomePageRequest extends StorefrontBaseRequest<HomePageResponse>{

	@Autowired
	private StoreFrontService storeFrontService;
	
	
	@Override
	protected HomePageResponse handleRequest() {
		List<Movie> movies = storeFrontService.listMovies();
		HomePageResponse response = new HomePageResponse();
		response.setMovies(movies);
		return response;
	}

}
