package com.application.order.controller.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.application.order.controller.dto.request.OrderRequest;
import com.application.order.controller.dto.response.OrderResponseDto;
import com.application.order.controller.dto.response.RestResponse;
import com.application.order.controller.exception.InvalidDataException;
import com.application.order.controller.exception.OrderNotFoundException;
import com.application.order.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderHandler {

	private OrderService orderService;

	@Autowired
	private OrderHandler(OrderService orderService) {
		this.orderService = orderService;
	}

	public ResponseEntity<?> handleRequest(RequestType requestType, Object requestData) {
		switch (requestType) {
		case CREATE_ORDER:
			OrderRequest orderToCreate = (OrderRequest) requestData;
			return createOrder(orderToCreate);
		case GET_ORDER:
			OrderRequest orderToGet = (OrderRequest) requestData;
			return getOrder(orderToGet);
		default:
			throw new IllegalArgumentException("Unsupported request type");
		}
	}

	private ResponseEntity<RestResponse<OrderResponseDto>> createOrder(OrderRequest orderRequest) {
		try {
			log.info("..Inside Handler create order...");

			RestResponse<OrderResponseDto> restResponse = new RestResponse<>();
			var dto = orderService.createOrder(orderRequest);
			restResponse.setOutput(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(restResponse);
		} catch (InvalidDataException ex) {
			return ValidationExceptionHandler.handle(ex);
		}
	}

	private ResponseEntity<RestResponse<OrderResponseDto>> getOrder(OrderRequest orderRequest) {
		try {
			log.info("..Inside Handler get order...");

			RestResponse<OrderResponseDto> restResponse = new RestResponse<>();
			var dto = orderService.getOrder(orderRequest);
			restResponse.setOutput(dto);
			return ResponseEntity.status(HttpStatus.FOUND).body(restResponse);
		} catch (OrderNotFoundException | InvalidDataException ex) {
			return ValidationExceptionHandler.handle(ex);
		}
	}

	public enum RequestType {
		CREATE_ORDER, GET_ORDER;

	}

}
