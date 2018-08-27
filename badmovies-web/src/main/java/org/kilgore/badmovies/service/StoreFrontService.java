package org.kilgore.badmovies.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.kilgore.badmovies.domain.ShoppingCart;
import org.kilgore.badmovies.entity.Movie;
import org.kilgore.badmovies.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StoreFrontService {

	@Autowired
	private MovieRepo movieRepository;
	
	@Autowired
	private HttpSession session;
	
	
	public ShoppingCart getShoppingCart() {
		ShoppingCart shoppingCart = null;
		if(session!=null) {
			shoppingCart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
			if(shoppingCart==null) {
				shoppingCart = new ShoppingCart();
				session.setAttribute("SHOPPING_CART", shoppingCart);
			}
		}
		
		
		return shoppingCart;
	}
	
	
	public List<Movie> listMovies(){
		List<Movie> movieList = new ArrayList<>();
		Iterable<Movie> movieIterable = movieRepository.findAll();
		if(movieIterable!=null) {
			movieIterable.forEach(movieList::add);
		}
		return movieList;
	}
	
	
	
	public List<Movie> searchMovies(String titleSearch){
		List<Movie> movieList = new ArrayList<>();
		Iterable<Movie> movieIterable = movieRepository.findByTitleContaining(titleSearch);
		if(movieIterable!=null) {
			movieIterable.forEach(movieList::add);
		}
		return movieList;	
	}
	
	public Page<Movie> listMovies(Integer pageNumber){
		Pageable pageable = PageRequest.of(pageNumber, 12);
		Page<Movie> page = movieRepository.findAll(pageable);
		return page;
	}


	public List<Movie> findMoviesById(List<Integer> movieIds) {
		List<Movie> movieList = new ArrayList<>();
		Iterable<Movie> movieIterable = movieRepository.findByIdIn(movieIds);
		if(movieIterable!=null) {
			movieIterable.forEach(movieList::add);
		}
		return movieList;
	}


	public Movie findMoviesById(Integer movieId) {
		Optional<Movie> optional= movieRepository.findById(movieId);
		Movie movie = null;
		if(optional.isPresent()) {
			movie=optional.get();
		}
		return movie;
	}
	
	
}
