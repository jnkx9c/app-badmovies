package org.kilgore.badmovies;

import static org.junit.Assert.assertNotNull;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.kilgore.badmovies.admin.importer.MovieDataImporterService;
import org.kilgore.badmovies.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MovieImportingTests {

	@Autowired
	private MovieDataImporterService movieDataImporterService;
	

	//@Test
	public void testMovieImport() {
		Movie importedMovie = movieDataImporterService.importMovie("tt0270846");
		assertNotNull(importedMovie);
		assertNotNull(importedMovie.getId());
	}

}
