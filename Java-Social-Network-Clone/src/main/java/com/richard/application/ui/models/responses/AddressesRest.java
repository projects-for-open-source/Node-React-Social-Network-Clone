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
public class AddressesRest implements Serializable {
	
	/** serialVersionUID: */
	private static final long serialVersionUID = 1L;
	
	/** addressId: */
	private String addressId;
	
	/** city: */
	private String city;
	
	/** country: */
	private String country;
	
	/** streetName: */
	private String streetName;
	
	/** postalCode: */
	private String postalCode;
	
	/** type: */
	private String type;
	
}