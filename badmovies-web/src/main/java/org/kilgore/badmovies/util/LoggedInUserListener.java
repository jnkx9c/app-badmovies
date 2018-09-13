package org.kilgore.badmovies.util;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kilgore.badmovies.domain.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class LoggedInUserListener implements
        ApplicationListener<AuthenticationSuccessEvent> {
	
	private final Log logger = LogFactory.getLog(getClass());
	
    @Autowired
    private HttpSession session;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
    	logger.info("onApplicationEvent called: " + event.getAuthentication().getPrincipal());
        if(session.getAttribute(AppConstants.SESS_SHOPPING_CART)==null) {
        	logger.debug("onApplicationEvent:  shopping cart was null in session.  Creating new shopping cart.");
        	session.setAttribute(AppConstants.SESS_SHOPPING_CART, new ShoppingCart());
        }

    }

}