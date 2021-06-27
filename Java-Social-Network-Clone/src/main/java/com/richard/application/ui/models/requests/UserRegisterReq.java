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
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Richard
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterReq implements Serializable {
	
	/** serialVersionUID: */
	private static final long serialVersionUID = 1L;
	
	/** firstName: */
	@NotBlank(message = "must not be blank")
	private String firstName;
	
	/** lastName: */
	@NotBlank(message = "must not be blank")
	private String lastName;
	
	/** email: */
	@NotBlank(message = "must not be blank")
	@Email(message = "must be a well-formed email address")
	private String email;
	
	/** password: */
	@NotBlank(message = "must not be blank")
	private String password;
	
	/** addresses: */
	private List<AddressReq> addresses;
	
}