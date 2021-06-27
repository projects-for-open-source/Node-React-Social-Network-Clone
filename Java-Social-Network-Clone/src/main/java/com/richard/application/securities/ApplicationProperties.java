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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * @author admin
 *
 */
public class ApplicationProperties {
	
	/**
	 * env - ApplicationProperties.java - Environment:
	 */
	@Autowired
	private Environment env;
	
	/**
	 * ApplicationProperties
	 *
	 * getTokenSecret
	 *
	 * @return
	 */
	public String getTokenSecret() {
		return env.getProperty("tokenSecret");
	}
	
}
