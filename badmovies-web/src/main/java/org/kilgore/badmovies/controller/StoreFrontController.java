package org.kilgore.badmovies.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.kilgore.badmovies.domain.ShoppingCart;
import org.kilgore.badmovies.domain.ShoppingCartItem;
import org.kilgore.badmovies.dto.DTO_EntityConversionUtils;
import org.kilgore.badmovies.dto.MovieDTO;
import org.kilgore.badmovies.dto.MovieListDTO;
import org.kilgore.badmovies.dto.SearchMoviesListDTO;
import org.kilgore.badmovies.entity.Movie;
import org.kilgore.badmovies.entity.Order;
import org.kilgore.badmovies.entity.OrderItem;
import org.kilgore.badmovies.entity.User;
import org.kilgore.badmovies.repo.MovieRepo;
import org.kilgore.badmovies.repo.OrderRepo;
import org.kilgore.badmovies.response.OrderDetailsResponse;
import org.kilgore.badmovies.response.OrderHistoryResponse;
import org.kilgore.badmovies.response.ProcessOrderResponse;
import org.kilgore.badmovies.response.ProductListingResponse;
import org.kilgore.badmovies.response.ShoppingCartPageResponse;
import org.kilgore.badmovies.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/storefront")
public class StoreFrontController {
	
	@Autowired
	private HttpSession session;	
	
	@Autowired
	private MovieRepo movieRepository;	
	
	@Autowired
	private OrderRepo orderRepository;	
	
	
	@RequestMapping({"/",""})
	public RedirectView redirectToProducts() {
		return new RedirectView("/storefront/products",true);
	}

	
	@RequestMapping({"/products"})
	public ModelAndView productlistingPage(@RequestParam(name="page", defaultValue="0") Integer page,Model model) {
		model.addAttribute("page",page);
		ProductListingResponse response = new ProductListingResponse();
		response.setShoppingCart(getShoppingCart());
		model.addAttribute("storefrontresponse",response);
		return new ModelAndView("store/storefront_basepage");
	}
	
	
	
	@RequestMapping("/shoppingcart")
	public ModelAndView shoppingCartPage(Model model) {
		ShoppingCartPageResponse response = new ShoppingCartPageResponse();
		ShoppingCart shoppingCart = getShoppingCart();

		shoppingCart.calculateTotal();
		response.setShoppingCart(shoppingCart);
		model.addAttribute("storefrontresponse",response);
		
		return new ModelAndView("store/storefront_basepage");
	}
	
	
	
	
	@RequestMapping("/moviedetails")
	public ModelAndView movieDetails(@RequestParam(AppConstants.PARAM_MOVIE_ID) Integer movieId,Model model) {
		Movie movie = findMovieById(movieId);
		MovieDTO movieDTO = DTO_EntityConversionUtils.convertMovieEntityToDTO(movie);
		ShoppingCart shoppingCart = getShoppingCart();
		if(shoppingCart!=null) {
			movieDTO.setInCart(shoppingCart.containsItem(movieId));
		}
		model.addAttribute("movie", movieDTO);
		return new ModelAndView("store/moviedetails-modal");
	}
	
	
	
	
	@RequestMapping("/processorder")
	public ModelAndView processorder(Authentication authentication, Model model) {
		ProcessOrderResponse response = new ProcessOrderResponse();
		Order order = createOrder(getUser(),getShoppingCart());
		response.setProcessedOrder(order);
		
		//finally, clear the shopping cart
		getShoppingCart().clear();	
		response.setShoppingCart(getShoppingCart());
		model.addAttribute("storefrontresponse",response);
		return new ModelAndView("store/storefront_basepage");
	}
	
	
	
	
	@RequestMapping("/orderdetails")
	public ModelAndView orderdetails(
			@RequestParam(name=AppConstants.PARAM_ORDER_ID, required=true) Integer orderid,
			Authentication authentication, 
			Model model) {
		
		OrderDetailsResponse response = new OrderDetailsResponse();
		Order order = orderRepository.findByIdAndUser(orderid,getUser());
		response.setOrder(order);
		response.setShoppingCart(getShoppingCart());
		model.addAttribute("storefrontresponse",response);

		return new ModelAndView("store/storefront_basepage");
	}
	
	
	
	
	
	@RequestMapping("/orderhistory")
	public ModelAndView orderhistory(Model model) {
		OrderHistoryResponse response = new OrderHistoryResponse();
		List<Order> orders = orderRepository.findByUser(getUser());
		response.setOrders(orders);
		response.setShoppingCart(getShoppingCart());
		model.addAttribute("storefrontresponse",response);

		return new ModelAndView("store/storefront_basepage");
	}
		
	
	

	private User getUser() {
		User user = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null) {
			user = ((User)authentication.getPrincipal());
		}
		return user;
		
	}
	
	
	/**
	 * 
	 * Start REST service handlers
	 * 
	 */
	
	@RequestMapping("/rest/searchmovies")
	@ResponseBody
	public SearchMoviesListDTO searchmovies(
			@RequestParam(name=AppConstants.PARAM_QUERY) String search) {
		
		List<Movie> movieList = new ArrayList<>();
		Iterable<Movie> movieIterable = movieRepository.findByTitleContaining(search);
		if(movieIterable!=null) {
			movieIterable.forEach(movieList::add);
		}
		
		List<MovieDTO> movieDTOs = DTO_EntityConversionUtils.convertMovieEntitiesToDTOs(movieList);
		SearchMoviesListDTO searchMovieListDTO = new SearchMoviesListDTO();
		searchMovieListDTO.setMovies(movieDTOs);
		return searchMovieListDTO;
	}
	
	
	
	@RequestMapping("/rest/movielist")
	@ResponseBody
	public MovieListDTO movielist(
			@RequestParam(name=AppConstants.PARAM_PAGE_SIZE, defaultValue="10") Integer pagesize,
			@RequestParam(name=AppConstants.PARAM_PAGE, defaultValue="0") Integer pageToFetch){
		
//		Page<Movie> page = null;
//
//		page = storeFrontService.listMovies(pageToFetch);

		Page<Movie> page = movieRepository.findAll(PageRequest.of(pageToFetch, 12));		
		
		List<MovieDTO> movieDTOs = DTO_EntityConversionUtils.convertMovieEntitiesToDTOs(page.getContent());
		//mark the movies that are already in the shopping cart
		ShoppingCart shoppingCart = getShoppingCart();
		if(shoppingCart!=null) {
			for(MovieDTO movie: movieDTOs) {
				movie.setInCart(shoppingCart.containsItem(movie.getMovieId()));
		
			}
		}
		MovieListDTO movieListDTO = new MovieListDTO();
		movieListDTO.setMovies(movieDTOs);
		movieListDTO.setTotalMovies(page.getTotalElements());
		movieListDTO.setPage(page.getNumber());
		movieListDTO.setTotalPages(page.getTotalPages());
	
		return movieListDTO;
	}
	
	
	
	
	@RequestMapping({"/rest/shoppingcart"})
	@ResponseBody
	public ShoppingCart shoppingCart(HttpSession session) {
		ShoppingCart shoppingCart = getShoppingCart();
		return shoppingCart;
	}
	
	
	
	
	@RequestMapping({"/rest/updateshoppingcart"})
	@ResponseBody
	public ShoppingCart updateShoppingCart(
			@RequestParam(name=AppConstants.PARAM_UPDATE_ACTION) String action,
			@RequestParam(name=AppConstants.PARAM_MOVIE_ID, required=true) Integer movieid, 
			HttpSession session) {
		ShoppingCart shoppingCart = getShoppingCart();
		if(action!=null && movieid!=null) {
			switch (action) {
			case "add":
				Movie movie = findMovieById(movieid);
				shoppingCart.addItem(movie);
				break;
			case "remove":
				if(shoppingCart.containsItem(movieid)) {
					shoppingCart.removeItem(movieid);
				}				
				break;
			default:
				break;
			}
		}
		
		return shoppingCart;
	}
	
	
	@RequestMapping("/rest/updatecartquantity")
	@ResponseBody
	public ShoppingCart updateCartQuantity(
			@RequestParam(name=AppConstants.PARAM_MOVIE_ID, required=true) Integer movieid,
			@RequestParam(name=AppConstants.PARAM_QUANTITY,required=true) Integer quantity){
		
		ShoppingCart shoppingCart = getShoppingCart();
		shoppingCart.updateQuantity(movieid, quantity);
		
		
		return shoppingCart;
		
	}
	

	
	private ShoppingCart getShoppingCart() {
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
	
	
	
	private Movie findMovieById(Integer movieId) {
		Optional<Movie> optional= movieRepository.findById(movieId);
		Movie movie = null;
		if(optional.isPresent()) {
			movie=optional.get();
		}
		return movie;
	}
	
	
	
	private Order createOrder(User user, ShoppingCart shoppingCart) {
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

	
	
}
