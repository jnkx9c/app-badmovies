package org.kilgore.badmovies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class BadmoviesWebApplication {

	public static void main(String[] args) {
		System.setProperty("http.proxyHost", "proxy.wellsfargo.com");
		System.setProperty("http.proxyPort", "8080");
		SpringApplication.run(BadmoviesWebApplication.class, args);
	}
	
	
	
	
	
}
