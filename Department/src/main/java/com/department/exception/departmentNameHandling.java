package com.department.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class departmentNameHandling {
	@ExceptionHandler(departmentNameShouldStartWithE.class)
	public ResponseEntity<String> handleDepartmentNameShouldStartWithE(departmentNameShouldStartWithE ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
