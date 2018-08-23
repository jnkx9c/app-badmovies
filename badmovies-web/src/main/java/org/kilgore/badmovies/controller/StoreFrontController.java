package org.kilgore.badmovies.controller;

import java.util.List;

import org.kilgore.badmovies.entity.Movie;
import org.kilgore.badmovies.service.StoreFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/storefront")
public class StoreFrontController {

	
	@Autowired
	private StoreFrontService storeFrontService;
	
	
	
	@RequestMapping({"/",""})
	public ModelAndView homepage(Model model) {
		List<Movie> movies = storeFrontService.listMovies();
		model.addAttribute("movies", movies);
		model.addAttribute("basepage","home");
		return new ModelAndView("store/storefront_basepage");
	}
	
	
}
