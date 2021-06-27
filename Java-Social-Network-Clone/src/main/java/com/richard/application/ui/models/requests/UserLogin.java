/**
 * Nickname: Richard
 * Email: richardvu.work@gmail.com
 * Skype: richardvu.work@gmail.com
 * Phone: (+84) 0935710974 - (+84) 0935810974
 * Country: Viet Nam
 * Year: 2021
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application.ui.models.requests;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Richard
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin implements Serializable {
	
	/** serialVersionUID: */
	private static final long serialVersionUID = 1L;
	
	/** email: */
	private String email = "ad@mail.com";
	
	/** password: */
	private String password;
	
}