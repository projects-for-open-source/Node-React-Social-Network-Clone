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

import lombok.Data;

/**
 * @author admin
 *
 */
@Data
public class ApiError {
	
	private String error;
	
	private String defaultMessage;
	
	public ApiError(String error, String defaultMessage) {
		this.error = error;
		this.defaultMessage = defaultMessage;
	}
	
}