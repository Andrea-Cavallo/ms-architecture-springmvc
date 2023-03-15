package com.application.product.controller.exception;

public abstract class ValidationException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ValidationException(String message) {
		super(message);
	}

}
