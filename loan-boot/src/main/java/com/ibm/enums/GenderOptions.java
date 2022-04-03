package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum GenderOptions {
	@JsonProperty("MALE")
	MALE,
	@JsonProperty("FEMALE")
	FEMALE,
}
