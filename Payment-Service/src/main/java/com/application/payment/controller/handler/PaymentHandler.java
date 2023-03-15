package com.application.payment.controller.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.application.payment.controller.dto.request.PaymentRequest;
import com.application.payment.controller.dto.response.PaymentResponse;
import com.application.payment.controller.dto.response.RestResponse;
import com.application.payment.controller.exception.InvalidPaymentException;
import com.application.payment.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PaymentHandler {

	private PaymentService paymentService;

	@Autowired
	private PaymentHandler(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public ResponseEntity<?> handleRequest(RequestType requestType, Object requestData) {
		switch (requestType) {
		case PAY:
			PaymentRequest payRequest = (PaymentRequest) requestData;
			return pay(payRequest);

		default:
			throw new IllegalArgumentException("Unsupported request type");
		}
	}

	private ResponseEntity<RestResponse<PaymentResponse>> pay(PaymentRequest payRequest) {
		try {
			log.info("..Inside Handler payment ...");

			RestResponse<PaymentResponse> restResponse = new RestResponse<>();
			var dto = paymentService.doPayment(payRequest);
			restResponse.setOutput(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(restResponse);
		} catch (InvalidPaymentException ex) {
			return ValidationExceptionHandler.handle(ex);
		}
	}

	public enum RequestType {
		PAY;

	}
}