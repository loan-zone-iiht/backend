package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Enum {PaymentMethod} is for the options defining
 * the methods of a transaction/payment.
 * 
 * @JsonProperty represents enums in JSON format.
 * It's mainly for reqest response objects in controllers.
 * 
 * 
 * @author Saswata Dutta
 */
public enum PaymentMethod {
	@JsonProperty("UPI")
	UPI, 
	@JsonProperty("CREDIT_CARD")
	CREDIT_CARD, 
	@JsonProperty("DEBIT_CARD")
	DEBIT_CARD, 
	@JsonProperty("BANK_TRANSFER")
	BANK_TRANSFER,
}
