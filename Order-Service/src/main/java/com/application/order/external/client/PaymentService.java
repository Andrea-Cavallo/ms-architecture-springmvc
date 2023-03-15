package com.application.order.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.application.order.external.request.PaymentRequest;

@FeignClient(name = "PAYMENT-SERVICE/pay")
public interface PaymentService {

	@PostMapping(value = "/pay", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> pay(@RequestBody PaymentRequest payRequest);

}
