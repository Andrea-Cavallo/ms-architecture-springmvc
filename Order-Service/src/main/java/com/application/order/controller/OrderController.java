package com.application.order.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.order.controller.dto.request.OrderRequest;
import com.application.order.controller.handler.OrderHandler;

@Validated
//@Tag(name = "Products", description = "Products API")
@RestController
public class OrderController {

	private OrderHandler orderHandler;

	@Autowired
	private OrderController(OrderHandler orderHandler) {

		this.orderHandler = orderHandler;
	}

	@PostMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody OrderRequest orderRequest) {
		return orderHandler.handleRequest(OrderHandler.RequestType.CREATE_ORDER, orderRequest);
	}

	@GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProduct(@RequestParam(required = false) String productId,
			@RequestParam(required = false) Long amount, @RequestParam(required = false) String orderStatus)

	{

		OrderRequest orderRequest = OrderRequest.builder().totalAmount(Optional.ofNullable(amount).orElse(0L))
				.productId(Optional.ofNullable(productId).orElse(""))
				.orderStatus(Optional.ofNullable(orderStatus).orElse(""))

				.build();

		return orderHandler.handleRequest(OrderHandler.RequestType.GET_ORDER, orderRequest);
	}
}