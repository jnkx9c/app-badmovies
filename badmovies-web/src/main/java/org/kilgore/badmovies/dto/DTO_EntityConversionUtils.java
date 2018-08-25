package org.kilgore.badmovies.dto;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.kilgore.badmovies.entity.Movie;

public class DTO_EntityConversionUtils {

	private static Locale locale = new Locale("en", "US");      
	private static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
	
	
	public static MovieDTO convertMovieEntityToDTO(Movie movie) {
		MovieDTO dto = new MovieDTO();
		dto.setMovieId(movie.getId());
		dto.setPoster(movie.getPoster());
		dto.setTitle(movie.getTitle());
		dto.setFormattedPrice(currencyFormatter.format(movie.getPrice()!=null?movie.getPrice():0));
		return dto;
	}
	
	
	public static List<MovieDTO>  convertMovieEntitiesToDTOs(List<Movie> movies){
		List<MovieDTO> movieDtos = new ArrayList<>();
		if(movies!=null) {
			for(Movie movie: movies) {
				movieDtos.add(convertMovieEntityToDTO(movie));
			}
		}
		return movieDtos;
	}
	
	
}
