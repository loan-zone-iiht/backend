package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SuccessType {
	@JsonProperty("SUCCESSFUL")
	SUCCESSFUL, 
	@JsonProperty("FAILED")
	FAILED
}
