/**
 * Nickname: Richard
 * Email: richardvu.work@gmail.com
 * Skype: richardvu.work@gmail.com
 * Phone: (+84) 0935710974 - (+84) 0935810974
 * Country: Viet Nam
 * Year: 2021
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 */
package com.richard.application.ui.models;

import java.util.List;

import javax.validation.Valid;

import com.richard.application.ui.models.requests.UserRegisterReq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author admin
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Testing {
	
	@Valid
	private List<UserRegisterReq> userRegister;
	
}
