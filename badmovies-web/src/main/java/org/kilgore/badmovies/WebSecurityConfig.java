package org.kilgore.badmovies;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.kilgore.badmovies.controller.StoreFrontController;
import org.kilgore.badmovies.entity.User;
import org.kilgore.badmovies.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   
	@Autowired
	private DataSource dataSource;
	
	@Autowired 
	private UserRepo userRepository;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/","/resources/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().hasRole("USER")
                .and()
            .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                .permitAll()
                .defaultSuccessUrl("/storefront")
                
                .and()
            .logout()
                .permitAll();
        http.csrf().disable();
        

    }
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("\r\n\r\n encoded password is   "+encoder.encode("password"));
	    return encoder;
	}

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
    	//JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
    	UserDetailsService customUserDetailsService = new UserDetailsService() {
			
			@Override
			@Transactional
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				User user = userRepository.findByUsername(username);
				if(user==null || user.getAuthorities()==null || user.getAuthorities().isEmpty()) {
					throw new UsernameNotFoundException("'"+username+"' was not found.");
				}
				return user;
			}
		};
    	return customUserDetailsService;
 
    }
}