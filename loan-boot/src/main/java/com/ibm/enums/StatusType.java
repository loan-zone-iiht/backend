package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Enum {StatusType} is for the options defining
 * the type of a loan/loandetail.
 * 
 * 
 * @JsonProperty represents enums in JSON format.
 * It's mainly for reqest response objects in controllers.
 * 
 * @author Saswata Dutta
 */


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
