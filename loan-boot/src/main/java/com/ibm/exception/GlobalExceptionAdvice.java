package com.ibm.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
/**
 * Class {GlobalExceptionAdvice} is the advice class of
 * exceptions for all the spring-boot controllers
 * 
 * @author Saswata Dutta
 */

@ControllerAdvice
public class GlobalExceptionAdvice {
	@ExceptionHandler(GlobalLoanException.class)
	public ResponseEntity<String> handleGbLoanException(GlobalLoanException excptn) {
		return new ResponseEntity<String>(excptn.getErrorMessage(), HttpStatus.OK);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElException(NoSuchElementException excptn) {
		return new ResponseEntity<String>("No such value present in DB", HttpStatus.OK);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgException(IllegalArgumentException excptn) {
		return new ResponseEntity<String>("Wrong value recieved, send proer value (maybe enums)",
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<String> handleSqlIntegrityException
	(SQLIntegrityConstraintViolationException excptn) {
		return new ResponseEntity<String>(excptn.getMessage(), HttpStatus.OK);
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	  public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException excptn) {
	    return new ResponseEntity<String>("File too large to upload!", HttpStatus.OK);
	  }

}
