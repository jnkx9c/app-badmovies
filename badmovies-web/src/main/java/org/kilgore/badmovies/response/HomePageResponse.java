package org.kilgore.badmovies.response;

import java.util.List;

import org.kilgore.badmovies.entity.Movie;

public class HomePageResponse extends StorefrontBaseResponse{

	private List<Movie> movies;
	
	
	public List<Movie> getMovies() {
		return movies;
	}


	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}


	@Override
	public String getView() {
		return "home";
	}

}
