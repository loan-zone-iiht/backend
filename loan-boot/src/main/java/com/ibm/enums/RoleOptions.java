package com.ibm.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Enum {RoleOptions} is for the options defining a user
 * is either a manager or customer.
 * We need this as we'll need privilege based routing.
 * 
 * @JsonProperty represents enums in JSON format. It's mainly for reqest
 *               response objects in controllers.
 * 
 * 
 * @author Saswata Dutta
 */

public enum RoleOptions {
	@JsonProperty("MANAGER")
	MANAGER, 
	@JsonProperty("CUSTOMER")
	CUSTOMER,
<<<<<<< HEAD
}
=======
}
>>>>>>> a81d845b5cb61ec43e5c806745901f20cd2afdbf
