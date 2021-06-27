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

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * @author Richard
 */
@Getter
public class ApplicationException extends RuntimeException {
	
	/**
	 * serialVersionUID - ApplicationException.java - long:
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * httpStatus - ApplicationException.java - HttpStatus:
	 */
	private HttpStatus httpStatus;
	
	/**
	 * @param message
	 */
	public ApplicationException(String message) {
		super(message);
	}
	
	/**
	 * @param message
	 * @param httpStatus
	 */
	public ApplicationException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
}