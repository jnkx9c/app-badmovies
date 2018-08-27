package org.kilgore.badmovies.controller;

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
import org.kilgore.badmovies.request.RequestFinderService;
import org.kilgore.badmovies.request.StorefrontBaseRequest;
import org.kilgore.badmovies.response.StorefrontBaseResponse;
import org.kilgore.badmovies.service.StoreFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	private HttpSession httpSession;
	
	@Autowired
	private StoreFrontService storeFrontService;
	
	@Autowired
	private RequestFinderService requestFinderService;
	
	
	@RequestMapping({"/",""})
	public RedirectView redirectToProducts() {
		return new RedirectView("/storefront/products");
	}

	
	@RequestMapping({"/products"})
	public ModelAndView productlistingPage(@RequestParam(name="page", defaultValue="0") Integer page,Model model) {
		handleRequest("products",model);
		model.addAttribute("page",page);
		return new ModelAndView("store/storefront_basepage");
	}
	
	@RequestMapping("/shoppingcart")
	public ModelAndView shoppingCartPage(Model model) {
		handleRequest("shoppingcartpage",model);
		return new ModelAndView("store/storefront_basepage");
	}
	
	@RequestMapping("/moviedetails")
	public ModelAndView movieDetails(@RequestParam("movieid") Integer movieId,Model model) {
		Movie movie = storeFrontService.findMoviesById(movieId);
		MovieDTO movieDTO = DTO_EntityConversionUtils.convertMovieEntityToDTO(movie);
		ShoppingCart shoppingCart = getShoppingCart();
		if(shoppingCart!=null && shoppingCart.getMovieIds()!=null) {
			movieDTO.setInCart(shoppingCart.getMovieIds().contains(movieId));
		}
		model.addAttribute("movie", movieDTO);
		return new ModelAndView("store/moviedetails-modal");
	}
	
	private void handleRequest(String requestName, Model model) {
		handleRequest(requestName, null,model);
	}
	
	private void handleRequest(String requestName, Map<String, String> parameters, Model model) {
		StorefrontBaseRequest<?> request = requestFinderService.findRequestByName(requestName);
		request.setParameters(parameters);
		StorefrontBaseResponse response = request.execute();
		model.addAttribute("storefrontresponse",response);
	}

	
	
	@RequestMapping("/rest/searchmovies")
	@ResponseBody
	public SearchMoviesListDTO searchmovies(@RequestParam(name="q") String search) {
		List<MovieDTO> movieDTOs = DTO_EntityConversionUtils.convertMovieEntitiesToDTOs(storeFrontService.searchMovies(search));
		SearchMoviesListDTO searchMovieListDTO = new SearchMoviesListDTO();
		searchMovieListDTO.setMovies(movieDTOs);
		return searchMovieListDTO;
	}
	
	@RequestMapping("/rest/movielist")
	@ResponseBody
	public MovieListDTO movielist(
			@RequestParam(name="pagesize", defaultValue="10") Integer pagesize,
			@RequestParam(name="page", defaultValue="0") Integer pageToFetch){
		
		Page<Movie> page = null;

		page = storeFrontService.listMovies(pageToFetch);
		
		
		
		List<MovieDTO> movieDTOs = DTO_EntityConversionUtils.convertMovieEntitiesToDTOs(page.getContent());
		//mark the movies that are already in the shopping cart
		ShoppingCart shoppingCart = getShoppingCart();
		if(shoppingCart!=null && shoppingCart.getMovieIds()!=null) {
			for(MovieDTO movie: movieDTOs) {
				movie.setInCart(shoppingCart.getMovieIds().contains(movie.getMovieId()));
		
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
			@RequestParam(name="action") String action,
			@RequestParam(name="movieid", required=true) Integer movieid, 
			HttpSession session) {
		ShoppingCart shoppingCart = getShoppingCart();
		if(action!=null && movieid!=null) {
			switch (action) {
			case "add":
				if(!shoppingCart.getMovieIds().contains(movieid)) {
					shoppingCart.getMovieIds().add(movieid);

				}				
				break;
			case "remove":
				if(shoppingCart.getMovieIds().contains(movieid)) {
					shoppingCart.getMovieIds().remove(movieid);
				}				
				break;
			default:
				break;
			}
		}
		return shoppingCart;
	}
	
	
	private ShoppingCart getShoppingCart() {
		return storeFrontService.getShoppingCart();
	}
//	public static ShoppingCart getShoppingCart(HttpSession session) {
//		ShoppingCart shoppingCart = null;
//		if(session!=null) {
//			shoppingCart = (ShoppingCart) session.getAttribute("SHOPPING_CART");
//			if(shoppingCart==null) {
//				shoppingCart = new ShoppingCart();
//				session.setAttribute("SHOPPING_CART", shoppingCart);
//			}
//		}
//		
//		
//		return shoppingCart;
//	}
	
	
}
