package org.kilgore.badmovies.admin.importer;

import java.util.ArrayList;
import java.util.List;

import org.kilgore.badmovies.entity.Movie;
import org.kilgore.badmovies.entity.Rating;

public class JsonToEntityConverter {

	
	public static Movie convertJsonDTOToMovie(MovieJsonDTO jsonDTO) {
		Movie retVal = null;
		if(jsonDTO!=null) {
			retVal = new Movie();
			retVal.setActors(jsonDTO.getActors());
			retVal.setAwards(jsonDTO.getAwards());
			retVal.setBoxOffice(jsonDTO.getBoxOffice());
			retVal.setCountry(jsonDTO.getCountry());
			retVal.setDvd(jsonDTO.getDvd());
			retVal.setGenre(jsonDTO.getGenre());
			retVal.setImdbID(jsonDTO.getImdbID());
			retVal.setImdbRating(jsonDTO.getImdbRating());
			retVal.setImdbVotes(jsonDTO.getImdbVotes());
			retVal.setLanguage(jsonDTO.getLanguage());
			retVal.setMetascore(jsonDTO.getMetascore());
			retVal.setPlot(jsonDTO.getPlot());
			retVal.setPoster(jsonDTO.getPoster());
			retVal.setProduction(jsonDTO.getProduction());
			retVal.setRated(jsonDTO.getRated());
			if(jsonDTO.getRatings()!=null) {
				List<Rating> ratings = new ArrayList<>();
				for(RatingsJsonDTO ratingsJsonDTO: jsonDTO.getRatings()) {
					Rating rating = convertJsonDTOToRating(ratingsJsonDTO);
					if(rating!=null) {
						ratings.add(rating);
					}
				}
			}
			
			//retVal.setRatings(ratings);
			retVal.setReleased(jsonDTO.getReleased());
			retVal.setResponse(jsonDTO.getResponse());
			retVal.setRuntime(jsonDTO.getRuntime());
			retVal.setTitle(jsonDTO.getTitle());
			retVal.setType(jsonDTO.getType());
			retVal.setWebsite(jsonDTO.getWebsite());
			retVal.setWriter(jsonDTO.getWriter());
			retVal.setYear(jsonDTO.getYear());
			
		}
		
		return retVal;
		
	}
	
	public static Rating convertJsonDTOToRating(RatingsJsonDTO jsonDTO) {
		Rating retVal = null;
		if(jsonDTO!=null) {
			retVal = new Rating();
			retVal.setSource(jsonDTO.getSource());
			retVal.setValue(jsonDTO.getValue());
		}
		return retVal;
	}
	
	
}
