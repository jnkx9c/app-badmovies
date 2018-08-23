package org.kilgore.badmovies.controller;

import org.kilgore.badmovies.admin.importer.MovieDataImporterService;
import org.kilgore.badmovies.entity.Movie;
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
	
	
}
