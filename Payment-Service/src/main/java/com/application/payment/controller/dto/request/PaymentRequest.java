package com.application.payment.controller.dto.request;

import com.application.payment.controller.dto.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

	private Long orderId;
	private Double amount;
	private String referenceNumber;
	private PaymentMode paymentMode;

}