package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
/***  Enum {GenderOptions} is for the options defining
 * the loan risk property.
 * 
 * @JsonProperty represents enums in JSON format.
 * It's mainly for reqest response objects in controllers.
**/
public enum GenderOptions {
	@JsonProperty("MALE")
	MALE,
	@JsonProperty("FEMALE")
	FEMALE,
}
