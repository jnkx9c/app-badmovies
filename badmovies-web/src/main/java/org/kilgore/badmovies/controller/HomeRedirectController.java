package org.kilgore.badmovies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeRedirectController {

	
	@RequestMapping("/")	
	public RedirectView redirectToStoreFront() {
		return new RedirectView("/storefront");
	}
	
	
	
}
