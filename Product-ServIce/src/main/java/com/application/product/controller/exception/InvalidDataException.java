package com.application.product.controller.exception;

public class InvalidDataException extends ValidationException {

	private static final long serialVersionUID = 1L;

	public InvalidDataException(String message, String specificMessage, ExceptionType exceptionType) {
		super(createMessage(exceptionType, message));
	}

	private static String createMessage(ExceptionType exceptionType, Object param) {
		switch (exceptionType) {

		case SPECIFIC_MESSAGE:
			return String.format("invalid   %s ", param);

		default:
			return "Invalid message";
		}
	}

	public enum ExceptionType {

		SPECIFIC_MESSAGE,

	}
}
