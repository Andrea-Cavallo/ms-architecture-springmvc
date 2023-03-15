package com.application.payment.controller.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.application.payment.controller.dto.Dto;
import com.application.payment.controller.dto.response.RestResponse;
import com.application.payment.controller.exception.ValidationException;

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
		case "InvalidPaymentException":
			httpStatus = HttpStatus.NOT_ACCEPTABLE;
			errorCode = "INVALID_PAYMENT";
			break;
		default:
			httpStatus = HttpStatus.NOT_ACCEPTABLE;
			errorCode = "LOGIC";
		}
		return ResponseEntity.status(httpStatus)
				.body(new RestResponse<>(Map.of("CODE", errorCode, "MESSAGE", ex.getMessage()), null));
	}

}
