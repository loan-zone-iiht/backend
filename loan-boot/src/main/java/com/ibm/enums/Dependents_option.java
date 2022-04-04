package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Dependents_option {
	@JsonProperty("1")
	one,
	@JsonProperty("2")
	two,
	@JsonProperty("3+")
	three_more,
}
