package org.kilgore.badmovies.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.kilgore.badmovies.domain.ShoppingCart;
import org.kilgore.badmovies.dto.DTO_EntityConversionUtils;
import org.kilgore.badmovies.dto.MovieDTO;
import org.kilgore.badmovies.dto.MovieListDTO;
import org.kilgore.badmovies.dto.SearchMoviesListDTO;
import org.kilgore.badmovies.entity.Movie;
import org.kilgore.badmovies.entity.User;
import org.kilgore.badmovies.request.RequestFinderService;
import org.kilgore.badmovies.request.StorefrontBaseRequest;
import org.kilgore.badmovies.response.StorefrontBaseResponse;
import org.kilgore.badmovies.service.StoreFrontService;
import org.kilgore.badmovies.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	private StoreFrontService storeFrontService;
	
	@Autowired
	private RequestFinderService requestFinderService;
	
	
	@RequestMapping({"/",""})
	public RedirectView redirectToProducts() {
		return new RedirectView("/storefront/products",true);
	}

	
	@RequestMapping({"/products"})
	public ModelAndView productlistingPage(@RequestParam(name="page", defaultValue="0") Integer page,Model model) {
		handleRequest(AppConstants.RB_PRODUCTS,model);
		model.addAttribute("page",page);
		return new ModelAndView("store/storefront_basepage");
	}
	
	
	
	@RequestMapping("/shoppingcart")
	public ModelAndView shoppingCartPage(Model model) {
		handleRequest(AppConstants.RB_SHOPPINGCART_PAGE,model);
		return new ModelAndView("store/storefront_basepage");
	}
	
	
	
	
	@RequestMapping("/moviedetails")
	public ModelAndView movieDetails(@RequestParam(AppConstants.PARAM_MOVIE_ID) Integer movieId,Model model) {
		Movie movie = storeFrontService.findMovieById(movieId);
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
		handleRequest(AppConstants.RB_PROCESS_ORDER, model);
		return new ModelAndView("store/storefront_basepage");
	}
	
	
	
	
	@RequestMapping("/orderdetails")
	public ModelAndView orderdetails(
			@RequestParam(name=AppConstants.PARAM_ORDER_ID, required=true) Integer orderid,
			Authentication authentication, 
			Model model) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(AppConstants.PARAM_ORDER_ID, orderid);
		handleRequest(AppConstants.RB_ORDER_DETAILS,parameters, model);
		return new ModelAndView("store/storefront_basepage");
	}
	
	
	
	
	
	@RequestMapping("/orderhistory")
	public ModelAndView orderhistory(Model model) {
		handleRequest(AppConstants.RB_ORDER_HISTORY, model);
		return new ModelAndView("store/storefront_basepage");
	}
		
	
	
	
	private void handleRequest(String requestName, Model model) {
		handleRequest(requestName, null,model);
	}
	
	private void handleRequest(String requestName, Map<String, Object> parameters, Model model) {
		StorefrontBaseRequest<?> request = requestFinderService.findRequestByName(requestName);
		request.setParameters(parameters);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null) {
			request.setUser((User)authentication.getPrincipal());
		}
		StorefrontBaseResponse response = request.execute();
		model.addAttribute("storefrontresponse",response);
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
		List<MovieDTO> movieDTOs = DTO_EntityConversionUtils.convertMovieEntitiesToDTOs(storeFrontService.searchMovies(search));
		SearchMoviesListDTO searchMovieListDTO = new SearchMoviesListDTO();
		searchMovieListDTO.setMovies(movieDTOs);
		return searchMovieListDTO;
	}
	
	
	
	@RequestMapping("/rest/movielist")
	@ResponseBody
	public MovieListDTO movielist(
			@RequestParam(name=AppConstants.PARAM_PAGE_SIZE, defaultValue="10") Integer pagesize,
			@RequestParam(name=AppConstants.PARAM_PAGE, defaultValue="0") Integer pageToFetch){
		
		Page<Movie> page = null;

		page = storeFrontService.listMovies(pageToFetch);
		
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
				Movie movie = storeFrontService.findMovieById(movieid);
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
		return storeFrontService.getShoppingCart();
	}

	
	
}
