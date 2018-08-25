package org.kilgore.badmovies.repo;

import java.util.List;

import org.kilgore.badmovies.entity.Movie;
import org.springframework.data.repository.CrudRepository;


public interface MovieRepo extends CrudRepository<Movie, Integer>{

	Movie findByImdbID(String imdbID);

	Iterable<Movie> findByIdIn(List<Integer> movieIds);

	
	
	
}
