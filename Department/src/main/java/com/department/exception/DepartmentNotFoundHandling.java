package com.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DepartmentNotFoundHandling {
	@ExceptionHandler(DepartmentNotFoundException.class)
	ResponseEntity<String> DepartmentNotFoundExceptionMethod(DepartmentNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
