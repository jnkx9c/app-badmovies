package org.kilgore.badmovies.admin.importer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.kilgore.badmovies.entity.Movie;
import org.kilgore.badmovies.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class MovieDataImporterService {

	
	
	private static ObjectMapper mapper = new ObjectMapper();
	private final static String apikey = "d4463eaa";
	static {
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		System.setProperty("http.proxyHost", "proxy.wellsfargo.com");
		System.setProperty("http.proxyPort", "8080");
	}
	
	@Autowired
	private MovieRepo movieRepository;
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		String apikey = "d4463eaa";
		String imdbId = "tt0270846";
		
		System.setProperty("http.proxyHost", "proxy.wellsfargo.com");
		System.setProperty("http.proxyPort", "8080");
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		MovieJsonDTO movie = mapper.readValue(new URL("http://www.omdbapi.com/?apikey=d4463eaa&i=tt0270846&plot=full"), MovieJsonDTO.class);
		System.out.println(movie);
		System.out.println(movie.getRatings());	
	}
	
	
	public Movie importMovie(String imdbId) {
		Movie retVal = null;
		
		Movie existingMovie = movieRepository.findByImdbID(imdbId);
		if(existingMovie==null) {
			try {
				MovieJsonDTO jsonMovie = mapper.readValue(new URL("http://www.omdbapi.com/?apikey="+apikey+"&i="+imdbId+"&plot=full"), MovieJsonDTO.class);
				Movie convertedMovie = JsonToEntityConverter.convertJsonDTOToMovie(jsonMovie);
				retVal = movieRepository.save(convertedMovie);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}else {
			retVal = existingMovie;
		}
		
		return retVal;
		
	}
	
	

}
