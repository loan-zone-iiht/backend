package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FromOptions {
	@JsonProperty("BANK")
	BANK,
	@JsonProperty("CUSTOMER")
	CUSTOMER,
}
