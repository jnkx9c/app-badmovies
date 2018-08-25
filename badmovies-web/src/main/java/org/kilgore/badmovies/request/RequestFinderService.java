package org.kilgore.badmovies.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class RequestFinderService {

	@Autowired
	private ApplicationContext applicationContext;
	
	
	
	public StorefrontBaseRequest<?> findRequestByName(String requestName) {
		return applicationContext.getBean(requestName, StorefrontBaseRequest.class);
	}
	
	
	
}
