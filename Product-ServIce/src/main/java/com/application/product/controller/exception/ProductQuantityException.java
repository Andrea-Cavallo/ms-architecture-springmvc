package com.application.product.controller.exception;

public class ProductQuantityException extends ValidationException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Long quantity;

	public ProductQuantityException(Long quantity) {
		super(String.format("Product %d is more than we have in our stock, you can't remove", quantity));
		this.quantity = quantity;
	}
}