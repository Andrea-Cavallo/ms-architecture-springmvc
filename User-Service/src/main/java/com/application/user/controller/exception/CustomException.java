
package com.application.user.controller.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4319192749946737370L;
	private String errorCode;
	private int status;

	public CustomException(String message, String errorCode, int status) {
		super(message);
		this.errorCode = errorCode;
		this.status = status;
	}
}