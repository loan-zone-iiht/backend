package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enum {PaymentType} is for the options defining
 * the type of a transaction/payment.
 * 
 * @JsonProperty represents enums in JSON format.
 * It's mainly for reqest response objects in controllers.
 * 
 * @author Saswata Dutta
 */

public enum PaymentType {
	@JsonProperty("REGULAR")
	REGULAR, 
	@JsonProperty("DOWNPAYMENT")
	DOWNPAYMENT, 
	@JsonProperty("FORECLOSURE")
	FORECLOSURE
}
