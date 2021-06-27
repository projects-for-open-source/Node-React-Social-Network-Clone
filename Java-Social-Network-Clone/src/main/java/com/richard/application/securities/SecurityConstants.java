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

/**
 * @author Richard
 */
public class SecurityConstants {
	
	/** EXPIRATION_TIME: */
	public static final long EXPIRATION_TIME = 864000000;
	
	/** PASSWORD_RESET_EXPIRATION_TIME: */
	public static final long PASSWORD_RESET_EXPIRATION_TIME = 3600000;
	
	/** TOKEN_PREFIX: */
	public static final String TOKEN_PREFIX = "Bearer ";
	
	/** HEADER_STRING: */
	public static final String HEADER_STRING = "Authorization";
	
	/** REGISTER_URL: */
	public static final String REGISTER_URL = "/users/create";
	
	/** VERIFICATION_EMAIL_URL: */
	public static final String VERIFICATION_EMAIL_URL = "/users/email-verification";
	
	/** PASSWORD_RESET_REQUEST_URL: */
	public static final String PASSWORD_RESET_REQUEST_URL = "/users/password-reset-request";
	
	/** PASSWORD_RESET_URL: */
	public static final String PASSWORD_RESET_URL = "/users/password-reset";
	
	/** H2_CONSOLE: */
	public static final String H2_CONSOLE = "/h2-console/**";
	
	/** VERIFICATION_EMAIL_USER: */
	public static final String VERIFICATION_EMAIL_USER = "REGISTER TO APPLICATION";
	
	/**
	 * getTokenSecret SecurityConstants
	 * 
	 * @return
	 */
	public static String getTokenSecret() {
		ApplicationProperties appProperties = (ApplicationProperties) SpringApplicationContext
		    .getBean("ApplicationProperties");
		return appProperties.getTokenSecret();
	}
	
}