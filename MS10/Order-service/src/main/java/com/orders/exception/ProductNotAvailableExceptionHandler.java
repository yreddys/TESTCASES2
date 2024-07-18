package com.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ProductNotAvailableExceptionHandler {
	@ExceptionHandler(ProductNotAvailableException.class)
	public ResponseEntity<String> handleProductNotAvailableException(ProductNotAvailableException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
