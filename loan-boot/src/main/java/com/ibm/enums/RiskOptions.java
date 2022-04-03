package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Enum {RiskOptions} is for the options defining
 * the prediction of Loan risk.
 * 
 * 
 * @JsonProperty represents enums in JSON format.
 * It's mainly for reqest response objects in controllers.
 * 
 * @author Saswata Dutta
 */
public enum RiskOptions {
	@JsonProperty("HIGH-RISK")
	HIGHRISK, 
	@JsonProperty("LOW-RISK")
	LOWRISK, 
}
