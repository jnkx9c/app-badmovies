package org.kilgore.badmovies.repo;

import java.util.Collection;
import java.util.List;

import org.kilgore.badmovies.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface MovieRepo extends CrudRepository<Movie, Integer>{

	Movie findByImdbID(String imdbID);

	Iterable<Movie> findByIdIn(Collection<Integer> movieIds);
	Page<Movie> findAll(Pageable pageable);
	Iterable<Movie> findByTitleContaining(String titleSearch);

	
	
	
}
