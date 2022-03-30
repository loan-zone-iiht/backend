package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enum {SuccessType} is for the options defining
 * the success type of a transaction/payment.
 * 
 * 
 * @JsonProperty represents enums in JSON format.
 * It's mainly for reqest response objects in controllers.
 * 
 * @author Saswata Dutta
 */

public enum SuccessType {
	@JsonProperty("SUCCESSFUL")
	SUCCESSFUL, 
	@JsonProperty("FAILED")
	FAILED
}
