package com.application.product.controller.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.application.product.controller.dto.request.ProductRequest;
import com.application.product.controller.dto.response.ProductDto;
import com.application.product.controller.dto.response.RestResponse;
import com.application.product.controller.exception.InvalidDataException;
import com.application.product.controller.exception.ProductNotFoundException;
import com.application.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductHandler {

	private ProductService productService;

	@Autowired
	private ProductHandler(ProductService productService) {
		this.productService = productService;
	}

	public ResponseEntity<?> handleRequest(RequestType requestType, Object requestData) {
		switch (requestType) {
		case CREATE_PRODUCT:
			ProductRequest productRequest = (ProductRequest) requestData;
			return createProduct(productRequest);
		case GET_PRODUCT_BY_EXAMPLE:
			ProductRequest productToFind = (ProductRequest) requestData;
			return getProduct(productToFind);
		case REDUCE_QUANTITY:
			ProductRequest reduceQuantity = (ProductRequest) requestData;
			return reduceQuantity(reduceQuantity);
		default:
			throw new IllegalArgumentException("Unsupported request type");
		}
	}

	private ResponseEntity<RestResponse<ProductDto>> createProduct(ProductRequest productRequest) {
		try {
			log.info("..Inside Handler create product...");

			RestResponse<ProductDto> restResponse = new RestResponse<>();
			var productDto = productService.createProduct(productRequest);
			restResponse.setOutput(productDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(restResponse);
		} catch (InvalidDataException ex) {
			return ValidationExceptionHandler.handle(ex);
		}
	}

	private ResponseEntity<RestResponse<ProductDto>> getProduct(ProductRequest productRequest) {
		try {
			log.info("..Inside Handler get product...");

			RestResponse<ProductDto> restResponse = new RestResponse<>();
			var productDto = productService.getProduct(productRequest);
			restResponse.setOutput(productDto);
			return ResponseEntity.status(HttpStatus.FOUND).body(restResponse);
		} catch (ProductNotFoundException | InvalidDataException ex) {
			return ValidationExceptionHandler.handle(ex);
		}
	}

	private ResponseEntity<RestResponse<ProductDto>> reduceQuantity(ProductRequest productRequest) {
		try {
			log.info("..Inside Handler reduce Quantity ..");

			RestResponse<ProductDto> restResponse = new RestResponse<>();
			var productDto = productService.reduceQuantity(productRequest);
			restResponse.setOutput(productDto);
			return ResponseEntity.status(HttpStatus.OK).body(restResponse);
		} catch (ProductNotFoundException | InvalidDataException ex) {
			return ValidationExceptionHandler.handle(ex);
		}
	}

	public enum RequestType {
		CREATE_PRODUCT, GET_PRODUCT_BY_EXAMPLE, REDUCE_QUANTITY;

	}

}
