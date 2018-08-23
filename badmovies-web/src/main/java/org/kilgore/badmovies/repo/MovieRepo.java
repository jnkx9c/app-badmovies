package org.kilgore.badmovies.repo;

import org.kilgore.badmovies.entity.Movie;
import org.springframework.data.repository.CrudRepository;


public interface MovieRepo extends CrudRepository<Movie, Integer>{

	Movie findByImdbID(String imdbID);

	
	
	
}
