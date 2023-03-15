package com.application.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.payment.controller.dto.request.PaymentRequest;
import com.application.payment.controller.handler.PaymentHandler;

@Validated
@RestController
public class PaymentController {

	private PaymentHandler paymentHandler;

	@Autowired
	private PaymentController(PaymentHandler paymentHandler) {

		this.paymentHandler = paymentHandler;
	}

	@PostMapping(value = "/pay", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> pay(@RequestBody PaymentRequest payRequest) {
		return paymentHandler.handleRequest(PaymentHandler.RequestType.PAY, payRequest);
	}

}