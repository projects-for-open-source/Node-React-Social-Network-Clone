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

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author admin
 *
 */
public class SpringApplicationContext implements ApplicationContextAware {
	
	/** CONTEXT: */
	private static ApplicationContext CONTEXT;
	
	/**
	 * setApplicationContext SpringApplicationContext
	 * 
	 * @param context
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		CONTEXT = context;
	}
	
	/**
	 * getBean SpringApplicationContext
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		return CONTEXT.getBean(beanName);
	}
	
}