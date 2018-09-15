package org.kilgore.badmovies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kilgore.badmovies.entity.Movie;
import org.kilgore.badmovies.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MovieRepositoryTests {

	@Autowired
	private MovieRepo movieRepo;
	
	//@Test
	public void contextLoads() {
		
		System.out.println("movieRepo = "+movieRepo);
	}
	
	
	//@Test
	public void testMovieCreate() {
		Movie movie = new Movie();
		movie.setTitle("Test Movie");
		
		Movie savedMovie = movieRepo.save(movie);
		
		assertEquals(movie.getTitle(), savedMovie.getTitle());
		assertNotNull(savedMovie.getId());
		
	}
	
}
