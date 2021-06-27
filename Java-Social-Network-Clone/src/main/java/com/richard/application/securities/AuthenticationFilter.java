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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.richard.application.services.UserService;
import com.richard.application.shared.dtos.UserDto;
import com.richard.application.ui.models.requests.UserLogin;
import com.richard.application.ui.models.responses.Token;
import com.richard.application.ui.models.responses.UserPrincipal;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author admin
 *
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	/** authenticationManager: */
	private final AuthenticationManager authenticationManager;
	
	/**
	 * @param authenticationManager
	 */
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	/**
	 * attemptAuthentication AuthenticationFilter
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
	    throws AuthenticationException {
		try {
			
			UserLogin creds = new ObjectMapper().readValue(req.getInputStream(), UserLogin.class);
			
			return authenticationManager.authenticate(
			    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * successfulAuthentication AuthenticationFilter
	 * 
	 * @param req
	 * @param response
	 * @param chain
	 * @param auth
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse response, FilterChain chain,
	    Authentication auth) throws IOException, ServletException {
		
		String email = ((UserPrincipal) auth.getPrincipal()).getUsername();
		auth.getPrincipal().hashCode();
		
		String token = Jwts.builder().setSubject(email)
		    .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
		    .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret()).compact();
		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
		UserDto userDto = userService.getUser(email);
		
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		response.addHeader("UserID", userDto.getUserId());
		
		Token tokenObject = new Token(SecurityConstants.TOKEN_PREFIX, SecurityConstants.HEADER_STRING,
		    SecurityConstants.TOKEN_PREFIX + token);
		
		Gson gson = new Gson();
		String json = gson.toJson(tokenObject);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(json);
		
	}
	
}