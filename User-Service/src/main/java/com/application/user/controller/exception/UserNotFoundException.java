package com.application.user.controller.exception;

public class UserNotFoundException extends ValidationException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String transactionID;

	public UserNotFoundException(String transactionID) {
		super(String.format("User %s was not found on the system", transactionID));
		this.transactionID = transactionID;
	}
}