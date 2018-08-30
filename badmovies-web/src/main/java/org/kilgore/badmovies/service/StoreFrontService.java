package org.kilgore.badmovies.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.kilgore.badmovies.domain.ShoppingCart;
import org.kilgore.badmovies.domain.ShoppingCartItem;
import org.kilgore.badmovies.entity.Movie;
import org.kilgore.badmovies.entity.Order;
import org.kilgore.badmovies.entity.OrderItem;
import org.kilgore.badmovies.entity.User;
import org.kilgore.badmovies.repo.MovieRepo;
import org.kilgore.badmovies.repo.OrderRepo;
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
	private OrderRepo orderRepository;
	
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


	public List<Movie> findMoviesById(Collection<Integer> movieIds) {
		List<Movie> movieList = new ArrayList<>();
		Iterable<Movie> movieIterable = movieRepository.findByIdIn(movieIds);
		if(movieIterable!=null) {
			movieIterable.forEach(movieList::add);
		}
		return movieList;
	}


	public Movie findMovieById(Integer movieId) {
		Optional<Movie> optional= movieRepository.findById(movieId);
		Movie movie = null;
		if(optional.isPresent()) {
			movie=optional.get();
		}
		return movie;
	}


	public Order createOrder(User user, ShoppingCart shoppingCart) {
		Order persistedOrder = null;
		int totalOrderQuantity=0;
		float totalOrderPrice = 0;
		if(user!=null && shoppingCart!=null) {

			Collection<ShoppingCartItem> shoppingCartItems = shoppingCart.getItemIdMap().values();
			if(shoppingCartItems!=null && !shoppingCartItems.isEmpty()) {
				Order order = new Order();
				order.setUser(user);
				order.setOrderDate(new Date());
				for(ShoppingCartItem shoppingCartItem: shoppingCartItems) {
					OrderItem orderItem = new OrderItem();
					Movie movie = findMovieById(shoppingCartItem.getItemId());
					orderItem.setMovie(movie);
					orderItem.setQuantity(shoppingCartItem.getQuantity());
					orderItem.setPrice(shoppingCartItem.getPrice());
					totalOrderQuantity += shoppingCartItem.getQuantity();
					totalOrderPrice += (shoppingCartItem.getPrice()*shoppingCartItem.getQuantity());
					order.addOrderItem(orderItem);
					
				}
				order.setTotalOrderPrice(totalOrderPrice);
				order.setTotalOrderQuantity(totalOrderQuantity);
				persistedOrder = orderRepository.save(order);
			}
			

		}

		
		return persistedOrder;
	}


	public Order findOrder(User user, Integer orderId) {
		return orderRepository.findByIdAndUser(orderId,user);
	}


	public List<Order> findOrders(User user) {
		return orderRepository.findByUser(user);
	}
	
	
}
