package org.kilgore.badmovies.request;

import org.kilgore.badmovies.response.ProductListingResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="products")
@Scope("prototype")
public class ProductListingRequest extends StorefrontBaseRequest<ProductListingResponse>{

	
	@Override
	protected ProductListingResponse handleRequest() {
		ProductListingResponse response = new ProductListingResponse();
		return response;
	}

}
