package com.application.product.controller.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.application.product.controller.dto.response.Dto;
import com.application.product.controller.dto.response.RestResponse;
import com.application.product.controller.exception.ValidationException;

public class ValidationExceptionHandler {

	private ValidationExceptionHandler() {
	}

	/**
	 * Handles a ValidationException by creating an appropriate response entity with
	 * a RestResponse object.
	 *
	 * @param ex  the ValidationException to handle
	 * @param <T> the type of the response DTO
	 * @return a ResponseEntity containing a RestResponse object with the error code
	 *         and message from the ValidationException
	 */
	public static <T extends Dto> ResponseEntity<RestResponse<T>> handle(ValidationException ex) {

		HttpStatus httpStatus;
		String errorCode;
		switch (ex.getClass().getSimpleName()) {
		case "InvalidDataException":
			httpStatus = HttpStatus.BAD_REQUEST;
			errorCode = "SEMANTIC";
			break;
		case "ProductNotFoundException":
			httpStatus = HttpStatus.NOT_FOUND;
			errorCode = "PRODUCT_NOT_FOUND";
			break;
		case "ProductQuantityException":
			httpStatus = HttpStatus.NOT_ACCEPTABLE;
			errorCode = "INSUFFICIENT_QUANTITY";
			break;
		default:
			httpStatus = HttpStatus.NOT_ACCEPTABLE;
			errorCode = "LOGIC";
		}
		return ResponseEntity.status(httpStatus)
				.body(new RestResponse<>(Map.of("CODE", errorCode, "MESSAGE", ex.getMessage()), null));
	}

}
