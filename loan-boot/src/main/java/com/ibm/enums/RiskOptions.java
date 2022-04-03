package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RiskOptions {
	@JsonProperty("HIGH-RISK")
	HIGHRISK, 
	@JsonProperty("LOW-RISK")
	LOWRISK, 
}
