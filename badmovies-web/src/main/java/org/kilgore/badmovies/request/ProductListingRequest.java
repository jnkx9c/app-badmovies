package org.kilgore.badmovies.request;

import org.kilgore.badmovies.response.ProductListingResponse;
import org.kilgore.badmovies.util.AppConstants;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(AppConstants.RB_PRODUCTS)
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ProductListingRequest extends StorefrontBaseRequest<ProductListingResponse>{

	
	@Override
	protected ProductListingResponse handleRequest() {
		ProductListingResponse response = new ProductListingResponse();
		return response;
	}

}
