package com.application.product.controller.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.application.product.controller.dto.response.RestResponse;

@ControllerAdvice
@Order(0)
public class GenericExceptionHandler {

	public static final String MESSAGE = "message";
	public static final String CODE = "code";
	private Map<String, String> errorMap = new HashMap<>();

	@ExceptionHandler(MissingRequestHeaderException.class)
	public final ResponseEntity<RestResponse<String>> handleStatusException(MissingRequestHeaderException ex) {
		errorMap.put(CODE, "SEMANTIC");
		errorMap.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(new RestResponse<>(errorMap, null), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public final ResponseEntity<RestResponse<String>> handleAllExceptions(HttpMessageNotReadableException ex) {
		errorMap.put(CODE, "JSON");
		errorMap.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(new RestResponse<>(errorMap, null), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<RestResponse<String>> handleAllExceptions(Exception ex) {
		errorMap.put(CODE, "SEMANTIC");
		errorMap.put(MESSAGE, ex.getMessage());
		return new ResponseEntity<>(new RestResponse<>(errorMap, null), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}