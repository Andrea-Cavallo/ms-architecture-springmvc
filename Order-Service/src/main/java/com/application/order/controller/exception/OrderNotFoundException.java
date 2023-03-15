package com.application.order.controller.exception;

public class OrderNotFoundException extends ValidationException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String id;

	public OrderNotFoundException(String id) {
		super(String.format("Order %s was not found on the system", id));
		this.id = id;
	}
}