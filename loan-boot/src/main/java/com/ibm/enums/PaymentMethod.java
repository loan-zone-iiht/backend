package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

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
