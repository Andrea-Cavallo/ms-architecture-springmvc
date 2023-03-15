package com.application.product.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.product.controller.dto.request.ProductRequest;
import com.application.product.controller.handler.ProductHandler;

@Validated
@RestController
public class ProductController {

	private ProductHandler productHandler;

	@Autowired
	private ProductController(ProductHandler productHandler) {

		this.productHandler = productHandler;
	}

	@PostMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE

	)
	public ResponseEntity<?> create(@RequestBody ProductRequest productRequest) {
		return productHandler.handleRequest(ProductHandler.RequestType.CREATE_PRODUCT, productRequest);
	}

	@GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProduct(@RequestParam(required = false) String productName,
			@RequestParam(required = false) Long price, @RequestParam(required = false) String productId) {

		ProductRequest productRequest = ProductRequest.builder().price(Optional.ofNullable(price).orElse(0L))
				.productName(Optional.ofNullable(productName).orElse(""))
				.productId(Optional.ofNullable(productId).orElse("")).build();

		return productHandler.handleRequest(ProductHandler.RequestType.GET_PRODUCT_BY_EXAMPLE, productRequest);
	}

	@PutMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> reduceQuantity

	(@RequestParam(required = true) String productId, @RequestParam(required = false) Long quantity

	) {

		ProductRequest productRequest = ProductRequest.builder().quantity(Optional.ofNullable(quantity).orElse(0L))
				.productId(Optional.ofNullable(productId).orElse("")).build();

		return productHandler.handleRequest(ProductHandler.RequestType.REDUCE_QUANTITY, productRequest);
	}

}