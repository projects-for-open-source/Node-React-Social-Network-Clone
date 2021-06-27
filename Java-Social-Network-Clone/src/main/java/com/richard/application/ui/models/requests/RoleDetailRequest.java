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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class RoleDetailRequest implements Serializable {
	
	/** serialVersionUID: */
	private static final long serialVersionUID = 5075638226567942023L;
	
	/** createUser: */
	@NotBlank(message = "createUser is mandatory")
	@NotEmpty
	@NotNull
	private String createUser;
	
	/** companyCode: */
	@NotBlank(message = "companyCode is mandatory")
	private String companyCode;
	
	/** roleDetailId: */
	private String roleDetailId;
	
	/** roleId: */
	private String roleId;
	
	/** featureCode: */
	@NotBlank(message = "featureCode is mandatory")
	private String featureCode;
	
	/** accessLevel: */
	@NotBlank(message = "accessLevel is mandatory")
	private Integer accessLevel;
	
	/** edit: */
	@NotBlank(message = "edit is mandatory")
	private Boolean edit = false;
	
	/** mailSend: */
	@NotBlank(message = "mailSend is mandatory")
	private Boolean mailSend = false;
	
	/** possibleToOutput: */
	@NotBlank(message = "possibleToOutput is mandatory")
	private Boolean possibleToOutput = false;
	
	/** possibleToImport: */
	@NotBlank(message = "possibleToImport is mandatory")
	private Boolean possibleToImport = false;
	
	/** followOrgId: */
	private List<String> followOrgId;
	
}
