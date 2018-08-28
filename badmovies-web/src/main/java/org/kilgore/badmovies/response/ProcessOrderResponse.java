package org.kilgore.badmovies.response;

public class ProcessOrderResponse extends StorefrontBaseResponse{

	@Override
	public String getView() {
		return "orderconfirmation";
	}

}
