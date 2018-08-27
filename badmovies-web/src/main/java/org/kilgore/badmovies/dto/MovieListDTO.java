package org.kilgore.badmovies.dto;

import java.io.Serializable;
import java.util.List;

public class MovieListDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MovieDTO> movies;
	private Integer page;
	private Long totalMovies;
	private Integer totalPages;
	
	public List<MovieDTO> getMovies() {
		return movies;
	}
	public void setMovies(List<MovieDTO> movies) {
		this.movies = movies;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Long getTotalMovies() {
		return totalMovies;
	}
	public void setTotalMovies(Long totalMovies) {
		this.totalMovies = totalMovies;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	
}
