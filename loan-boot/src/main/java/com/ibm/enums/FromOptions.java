package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enum {FromOptions} is for the options defining
 * the sender of a transaction/payment.
 * 
 * @JsonProperty represents enums in JSON format.
 * It's mainly for reqest response objects in controllers.
 * 
 * 
 * @author Saswata Dutta
 */

public enum FromOptions {
	@JsonProperty("BANK")
	BANK,
	@JsonProperty("CUSTOMER")
	CUSTOMER,
}
