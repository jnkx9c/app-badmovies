package org.kilgore.badmovies;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.kilgore.badmovies.controller.StoreFrontController;
import org.kilgore.badmovies.entity.Authority;
import org.kilgore.badmovies.entity.User;
import org.kilgore.badmovies.repo.AuthorityRepo;
import org.kilgore.badmovies.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   
	@Autowired
	private DataSource dataSource;
	
	@Autowired 
	private UserRepo userRepository;
	
	@Autowired
	private AuthorityRepo authorityRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/","/home","/loginpage","/registrationpage","/register","/resources/**","/favicon.ico").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().hasRole("USER")
                .and()
            .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                .permitAll()
                .defaultSuccessUrl("/storefront")
                .failureUrl("/loginpage?error=true")           
                .and()
            .logout()
            	.logoutSuccessUrl("/")
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
    public UserDetailsManager userDetailsService() {
//    	//JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//    	UserDetailsService customUserDetailsService = new UserDetailsService() {
//			
//			@Override
//			@Transactional
//			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//				User user = userRepository.findByUsername(username);
//				if(user==null || user.getAuthorities()==null || user.getAuthorities().isEmpty()) {
//					throw new UsernameNotFoundException("'"+username+"' was not found.");
//				}
//				return user;
//			}
//		};
		
		UserDetailsManager customUserDetailsManager = new UserDetailsManager() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				User user = userRepository.findByUsername(username);
				if(user==null || user.getAuthorities()==null || user.getAuthorities().isEmpty()) {
					throw new UsernameNotFoundException("'"+username+"' was not found.");
				}
				return user;
			}
			
			@Override
			public boolean userExists(String username) {
				return loadUserByUsername(username)!=null;
			}
			
			@Override
			public void updateUser(UserDetails user) {
				User siteUser = (User) user;
				
			}
			
			@Override
			public void deleteUser(String username) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void createUser(UserDetails user) {
				//all site users must have the USER role.  give it to them.
				Authority userAuthority =  authorityRepo.findByAuthority("ROLE_USER");
				((User)user).addAuthority(userAuthority);
				
				//since this is a new user, it's password is plaintext.  Need to encrypt to save in the database.
				String encryptedPassword =passwordEncoder.encode(user.getPassword());
				((User)user).setPassword(encryptedPassword);
				
				//save the user
				userRepository.save((User)user);
				
			}
			
			@Override
			public void changePassword(String oldPassword, String newPassword) {
				// TODO Auto-generated method stub
				
			}
		};
    	return customUserDetailsManager;
 
    }
}