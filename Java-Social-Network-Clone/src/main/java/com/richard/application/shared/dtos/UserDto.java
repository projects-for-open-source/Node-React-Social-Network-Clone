/**
 * Nickname: Richard
 * Email: richardvu.work@gmail.com
 * Skype: richardvu.work@gmail.com
 * Phone: (+84) 0935710974 - (+84) 0935810974
 * Country: Viet Nam
 * Year: 2021
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application.shared.dtos;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Richard
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
	
	/** serialVersionUID: */
	private static final long serialVersionUID = 1L;
	
	/** id: */
	private long id;
	
	/** userId: */
	private String userId;
	
	/** firstName: */
	private String firstName;
	
	/** lastName: */
	private String lastName;
	
	/** email: */
	private String email;
	
	/** encryptedPassword: */
	private String encryptedPassword;
	
	/** password: */
	private String password;
	
	/** emailVerificationToken: */
	private String emailVerificationToken;
	
	/** emailVerificationStatus: */
	private Boolean emailVerificationStatus = false;
	
	/** addresses: */
	private List<AddressDTO> addresses;
	
	/** roles: */
	private Collection<String> roles;
	
}
