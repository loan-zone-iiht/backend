package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum property_type_options {
	@JsonProperty("Urban")
	Urban,
	@JsonProperty("Rural")
	Rural,
	@JsonProperty("Semiurban")
	Semiurban,
}
