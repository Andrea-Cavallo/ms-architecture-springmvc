package com.application.product.controller.exception;

public class ProductNotFoundException extends ValidationException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String name;

	public ProductNotFoundException(String name) {
		super(String.format("Product %s was not found on the system", name));
		this.name = name;
	}
}