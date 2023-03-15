package com.application.user.controller.exception;

public class UserNotFoundException extends ValidationException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String userId;

	public UserNotFoundException(String userId) {
		super(String.format("User %s was not found on the system", userId));
		this.userId = userId;
	}
}