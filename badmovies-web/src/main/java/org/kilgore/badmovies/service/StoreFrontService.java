package org.kilgore.badmovies.service;

import java.util.ArrayList;
import java.util.List;

import org.kilgore.badmovies.entity.Movie;
import org.kilgore.badmovies.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreFrontService {

	@Autowired
	private MovieRepo movieRepository;
	
	
	
	
	
	public List<Movie> listMovies(){
		List<Movie> movieList = new ArrayList<>();
		Iterable<Movie> movieIterable = movieRepository.findAll();
		if(movieIterable!=null) {
			movieIterable.forEach(movieList::add);
		}
		return movieList;
		
	}
	
	
}
