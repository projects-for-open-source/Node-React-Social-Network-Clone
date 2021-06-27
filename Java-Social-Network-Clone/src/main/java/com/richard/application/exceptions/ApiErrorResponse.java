/**
 * Nickname: Richard
 * Email: richardvu.work@gmail.com
 * Skype: richardvu.work@gmail.com
 * Phone: (+84) 0935710974 - (+84) 0935810974
 * Country: Viet Nam
 * Year: 2021
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * @author admin
 *
 */
@Data
public class ApiErrorResponse<T> {
	
	/**
	 * status - ApiErrorResponse.java - HttpStatus:
	 */
	private HttpStatus status;
	
	/**
	 * message - ApiErrorResponse.java - String:
	 */
	private String message;
	
	/**
	 * errors - ApiErrorResponse.java - List<T>:
	 */
	private List<T> errors;
	
	/**
	 * @param status
	 * @param message
	 * @param errors
	 */
	public ApiErrorResponse(HttpStatus status, String message, List<T> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}
	
}