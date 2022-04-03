package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StatusType {
	@JsonProperty("ACCEPTED")
	ACCEPTED, 
	@JsonProperty("REJECTED")
	REJECTED, 
	@JsonProperty("PENDING")
	PENDING, 
	@JsonProperty("FORECLOSURE_PENDING")
	FORECLOSURE_PENDING,
	@JsonProperty("FORECLOSURE_ACCEPTED")
	FORECLOSURE_ACCEPTED, 
	@JsonProperty("COMPLETED")
	COMPLETED;
	
	
}
