/**
 * Nickname: Richard
 * Email: richardvu.work@gmail.com
 * Skype: richardvu.work@gmail.com
 * Phone: (+84) 0935710974 - (+84) 0935810974
 * Country: Viet Nam
 * Year: 2021
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application.ui.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author admin
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class Token {
	
	/**
	 * authorization - Token.java - String:
	 */
	private String authorization;
	
	/**
	 * headerString - Token.java - String:
	 */
	public String headerString;
	
	/**
	 * token - Token.java - String:
	 */
	private String token;
	
}
