package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PaymentType {
	@JsonProperty("REGULAR")
	REGULAR, 
	@JsonProperty("DOWNPAYMENT")
	DOWNPAYMENT, 
	@JsonProperty("FORECLOSURE")
	FORECLOSURE
}
