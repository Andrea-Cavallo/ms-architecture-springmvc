package com.application.user.controller.exception;

import lombok.Getter;

@Getter
public class InvalidEmailException extends ValidationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7164344803162465905L;

	public InvalidEmailException() {
		super("Your Email is not Valid");
	}
}
