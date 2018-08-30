package org.kilgore.badmovies;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class BadmoviesWebApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		System.setProperty("http.proxyHost", "proxy.wellsfargo.com");
		System.setProperty("http.proxyPort", "8080");
		SpringApplication.run(BadmoviesWebApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(BadmoviesWebApplication.class);
	}


	
	
	
}
