package com.application.user.controller.exception;

import lombok.Getter;

@Getter
public class InvalidEmailException extends ValidationException {

	public InvalidEmailException() {
		super("Your Email is not Valid");
	}
}
