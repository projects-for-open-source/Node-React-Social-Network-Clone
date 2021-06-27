/**
 * Nickname: Richard
 * Email: richardvu.work@gmail.com
 * Skype: richardvu.work@gmail.com
 * Phone: (+84) 0935710974 - (+84) 0935810974
 * Country: Viet Nam
 * Year: 2021
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application.ui.controllers.users;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.richard.application.ui.models.requests.UserLogin;
import com.richard.application.ui.models.responses.Token;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

/**
 * @author admin
 *
 */
@RestController
public class AuthenticationController {
	
	/**
	 * theFakeLogin AuthenticationController
	 * 
	 * @param userLogin
	 */
	@ApiOperation("User login")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Response Headers", responseHeaders = {
	        @ResponseHeader(name = "authorization", description = "Bearer <JWT value here>"),
	        @ResponseHeader(name = "userId", description = "<Public User Id value here>") }),
	    @ApiResponse(code = 200, message = "Response Body", response = Token.class) })
	@PostMapping("/users/login")
	public void theFakeLogin(@RequestBody UserLogin userLogin) {
		throw new IllegalStateException("This method should not be called. This method is implemented by Spring Security");
	}
	
}