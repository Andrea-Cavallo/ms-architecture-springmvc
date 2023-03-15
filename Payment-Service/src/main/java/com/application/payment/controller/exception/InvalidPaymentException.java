package com.application.payment.controller.exception;

public class InvalidPaymentException extends ValidationException {

	private static final long serialVersionUID = 1L;

	public InvalidPaymentException(String message, String specificMessage, ExceptionType exceptionType) {
		super(createMessage(exceptionType, message));
	}

	private static String createMessage(ExceptionType exceptionType, Object param) {
		switch (exceptionType) {

		case SPECIFIC_MESSAGE:
			return String.format("invalid  payment  %s ", param);

		default:
			return "Invalid message";
		}
	}

	public enum ExceptionType {

		SPECIFIC_MESSAGE,

	}
}
