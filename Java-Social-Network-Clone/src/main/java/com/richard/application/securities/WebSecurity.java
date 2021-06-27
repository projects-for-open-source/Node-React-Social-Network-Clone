/**
 * Nickname: Richard
 * Email: richardvu.work@gmail.com
 * Skype: richardvu.work@gmail.com
 * Phone: (+84) 0935710974 - (+84) 0935810974
 * Country: Viet Nam
 * Year: 2021
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application.securities;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.richard.application.io.repositories.UserRepository;
import com.richard.application.services.UserService;

/**
 * @author admin
 *
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	/** userDetailsService: */
	private final UserService userDetailsService;
	
	/** bCryptPasswordEncoder: */
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/** userRepository: */
	private final UserRepository userRepository;
	
	/**
	 * @param userDetailsService
	 * @param bCryptPasswordEncoder
	 * @param userRepository
	 */
	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder,
	    UserRepository userRepository) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRepository = userRepository;
	}
	
	/**
	 * configure WebSecurity
	 * 
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SecurityConstants.REGISTER_URL)
		    .permitAll()
		    .antMatchers("/", "/users/login", "/users/email-verification**", "/v2/api-docs", "/configuration/**",
		        "/swagger*/**", "/webjars/**", "/users/v2Checking")
		    .permitAll().anyRequest().authenticated().and().addFilter(getAuthenticationFilter())
		    .addFilter(new AuthorizationFilter(authenticationManager(), userRepository)).sessionManagement()
		    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.headers().frameOptions().disable();
	}
	
	/**
	 * configure WebSecurity
	 * 
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	/**
	 * getAuthenticationFilter WebSecurity
	 * 
	 * @return
	 * @throws Exception
	 */
	protected AuthenticationFilter getAuthenticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}
	
	/**
	 * corsConfigurationSource WebSecurity
	 * 
	 * @return
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("*"));
		
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
	
}