package org.kilgore.badmovies.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.kilgore.badmovies.admin.importer.MovieDataImporterService;
import org.kilgore.badmovies.entity.Movie;
import org.kilgore.badmovies.entity.Order;
import org.kilgore.badmovies.entity.User;
import org.kilgore.badmovies.repo.OrderRepo;
import org.kilgore.badmovies.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private MovieDataImporterService importerService;
	
	@Autowired
	private UserRepo userRepository;
	
	@Autowired
	private OrderRepo orderRepository;
	
	
	@RequestMapping({"/",""})
	public ModelAndView adminHomePage() {
		System.out.println("Hey... I'm being controlled here");
		return new ModelAndView("admin/home");
	}
	
	
	@RequestMapping("/importmovie")
	public ModelAndView importMovie(@RequestParam(name="imdbMovieId") String imdbMovieId, Model model) {
		System.out.println("importMovie called... imdbMovieId="+imdbMovieId);
		Movie movie = importerService.importMovie(imdbMovieId);
		model.addAttribute(movie);
		
		return new ModelAndView("admin/importresult");
	}
	
	@RequestMapping("/viewusers")
	public ModelAndView viewUsers(Model model) {
		
		List<User> users = new ArrayList<>();
		Iterable<User> userIterable = userRepository.findAll();
		Iterator<User> userIterator = userIterable.iterator();
		while(userIterator.hasNext()) {
			users.add(userIterator.next());
		}
		model.addAttribute("users",users);
		return new ModelAndView("admin/viewusers");
	}
	
	@RequestMapping("/viewuserdetails")
	public ModelAndView viewUserDetails(@RequestParam(name="userid",required=true) Integer userId,Model model) {
		
		Optional<User> userOptional = userRepository.findById(userId);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			model.addAttribute("user",user);
			List<Order> orders = orderRepository.findByUser(user);
			model.addAttribute("orders",orders);
		}
		return new ModelAndView("admin/viewuserdetails");
	}
	
	
	
	
}
