package org.kilgore.badmovies.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.kilgore.badmovies.domain.ShoppingCart;
import org.kilgore.badmovies.entity.Movie;
import org.kilgore.badmovies.request.RequestFinderService;
import org.kilgore.badmovies.request.StorefrontBaseRequest;
import org.kilgore.badmovies.response.StorefrontBaseResponse;
import org.kilgore.badmovies.service.StoreFrontService;
import org.springframework.beans.factory.annotation.Autowired;
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
	public RedirectView splashpage(Model model) {

		return new RedirectView("/storefront/home");
	}
	
	@RequestMapping({"/home"})
	public ModelAndView homepage(Model model,HttpSession session) {

		handleRequest("home",model,session);
		return new ModelAndView("store/storefront_basepage");
	}
	
	@RequestMapping("/shoppingcart")
	public ModelAndView shoppingCartPage(Model model, HttpSession session) {
		handleRequest("shoppingcartpage",model,session);
		return new ModelAndView("store/storefront_basepage");
	}
	
	@RequestMapping("/moviedetails")
	public ModelAndView movieDetails(@RequestParam("movieid") Integer movieId,Model model) {
		Movie movie = storeFrontService.findMoviesById(movieId);
		model.addAttribute("movie", movie);
		return new ModelAndView("store/moviedetails-modal");
	}
	
	private void handleRequest(String requestName, Model model, HttpSession session) {
		StorefrontBaseRequest<?> request = requestFinderService.findRequestByName(requestName);
		StorefrontBaseResponse response = request.execute();
		model.addAttribute("storefrontresponse",response);
	}

	
	
	
	

	
	@RequestMapping({"/rest/shoppingcart"})
	@ResponseBody
	public ShoppingCart shoppingCart(HttpSession session) {
		ShoppingCart shoppingCart = getShoppingCart(session);
		return shoppingCart;
	}
	
	@RequestMapping({"/rest/updateshoppingcart"})
	@ResponseBody
	public ShoppingCart updateShoppingCart(@RequestParam(name="movieid", required=true) Integer movieid, HttpSession session) {
		ShoppingCart shoppingCart = getShoppingCart(session);
		if(!shoppingCart.getMovieIds().contains(movieid)) {
			shoppingCart.getMovieIds().add(movieid);

		}
		return shoppingCart;
	}
	
	
	
	public static ShoppingCart getShoppingCart(HttpSession session) {
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
	
	
}
