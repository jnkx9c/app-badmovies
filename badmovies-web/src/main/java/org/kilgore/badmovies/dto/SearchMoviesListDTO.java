package org.kilgore.badmovies.dto;

import java.io.Serializable;
import java.util.List;

public class SearchMoviesListDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MovieDTO> movies;

	public List<MovieDTO> getMovies() {
		return movies;
	}

	public void setMovies(List<MovieDTO> movies) {
		this.movies = movies;
	}
	
	
}
