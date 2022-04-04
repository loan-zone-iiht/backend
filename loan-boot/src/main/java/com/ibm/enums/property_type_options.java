package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Enum {propertyType} is for the options defining
 * the features of application help to predict Risk insights..
 * 
 * 
 * @JsonProperty represents enums in JSON format.
 * It's mainly for reqest response objects in controllers.
 * 
 * @author Subhajit Sanyal
 */
public enum property_type_options {
	@JsonProperty("Urban")
	Urban,
	@JsonProperty("Rural")
	Rural,
	@JsonProperty("Semiurban")
	Semiurban,
}
