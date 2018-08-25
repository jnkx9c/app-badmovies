package org.kilgore.badmovies.dto;

import java.io.Serializable;
/**
 * This class is used to shuttle a smaller version of the Movie entity over json
 * @author Jeff
 *
 */
public class MovieDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer movieId;
	private String title;
	private String poster;
	private String formattedPrice;
	private boolean inCart =false;
	
	
	
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getFormattedPrice() {
		return formattedPrice;
	}
	public void setFormattedPrice(String formattedPrice) {
		this.formattedPrice = formattedPrice;
	}
	public boolean isInCart() {
		return inCart;
	}
	public void setInCart(boolean inCart) {
		this.inCart = inCart;
	}
	
	
	
	
}
