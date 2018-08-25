package org.kilgore.badmovies.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
public class ShoppingCart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<Integer> movieIds = new ArrayList<>();

	public List<Integer> getMovieIds() {
		return movieIds;
	}
	
	public Integer getItemCount() {
		return movieIds.size();
	}
	

}
