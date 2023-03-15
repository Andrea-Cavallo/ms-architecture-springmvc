
package com.application.payment.controller.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1899226406154758926L;
	private String errorCode;
	private int status;

	public CustomException(String message, String errorCode, int status) {
		super(message);
		this.errorCode = errorCode;
		this.status = status;
	}
}