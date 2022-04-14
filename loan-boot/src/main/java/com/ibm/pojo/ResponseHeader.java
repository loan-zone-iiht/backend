package com.ibm.pojo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Class {ResponseHeader} is a simple header class
 * to give success response.  
 * 
 * @author Saswata Dutta
 */
@Component
public class ResponseHeader {
	MultiValueMap<String, String> headers = new HttpHeaders();
	
	// exposing a custom header for error handling.
	public ResponseHeader() {
		headers.add("Access-Control-Expose-Headers", "success");
	}

	public MultiValueMap<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(MultiValueMap<String, String> headers) {
		this.headers = headers;
	}
	
	public MultiValueMap<String,String> putOnMap(String key, String value) {
		headers.add(key, value);
		return headers;
	}
	
}
