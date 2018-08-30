package org.kilgore.badmovies.admin.importer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.kilgore.badmovies.entity.Movie;
import org.kilgore.badmovies.entity.Rating;
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
	
	@PostConstruct
	public void initDatabase() {
		importMovie("tt0065421");
		importMovie("tt0448694");
		importMovie("tt0095776");
		
		importMovie("tt0037558");
		importMovie("tt0034583");
		importMovie("tt0112471");
		importMovie("tt0381681");
		importMovie("tt0053472");
		importMovie("tt0118694");
		importMovie("tt0053604");
		importMovie("tt0091167");
		importMovie("tt0338013");
		importMovie("tt0091867");
		importMovie("tt0055032");
		importMovie("tt0047811");
		importMovie("tt0031381");
		importMovie("tt0050105");
		importMovie("tt0058450");
		importMovie("tt0335266");
		importMovie("tt0046250");
		importMovie("tt0910970");
		importMovie("tt0064612");
		importMovie("tt0046511");
		importMovie("tt0059113");
		importMovie("tt0067185");
		importMovie("tt0098635");
		importMovie("tt0098258");
		importMovie("tt0097322");
		importMovie("tt0038733");

		
		
		//https://www.imdb.com/list/ls066657261/
		
	}
	
	
	public Movie importMovie(String imdbId) {
		Movie retVal = null;
		
		Movie existingMovie = movieRepository.findByImdbID(imdbId);
		if(existingMovie==null) {
			try {
				MovieJsonDTO jsonMovie = mapper.readValue(new URL("http://www.omdbapi.com/?apikey="+apikey+"&i="+imdbId+"&plot=full"), MovieJsonDTO.class);
				Movie convertedMovie = JsonToEntityConverter.convertJsonDTOToMovie(jsonMovie);
				convertedMovie.setPrice(1.99f);

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
