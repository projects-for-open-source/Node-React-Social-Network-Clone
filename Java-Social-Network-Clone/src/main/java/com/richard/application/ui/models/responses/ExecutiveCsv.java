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

import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author admin
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecutiveCsv {
	
	private Integer dispOrder;
	
	private String executiveCode;
	
	private String executiveName;
	
	public String toCsvString(String linefeedCharacters) {
		StringBuilder staffSb = new StringBuilder();
		
		if (dispOrder != null) {
			staffSb.append('"').append(replaceValue(dispOrder.toString())).append('"');
		}
		
		staffSb.append(",");
		
		if (!StringUtils.isEmpty(executiveName)) {
			staffSb.append('"').append(replaceValue(executiveName)).append('"');
		}
		
		staffSb.append(",");
		
		if (!StringUtils.isEmpty(executiveCode)) {
			staffSb.append('"').append(replaceValue(executiveCode)).append('"');
		}
		
		String staffStr = staffSb.toString();
		return new StringBuilder().append(staffStr).append(linefeedCharacters).toString();
	}
	
	public String replaceValue(String value) {
		if (value.indexOf('"') >= 0)
			value = value.replace("\"", "\"\"");
		return value;
	}
	
}
