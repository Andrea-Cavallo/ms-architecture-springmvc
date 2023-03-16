package com.application.payment.controller.dto.response;

import java.time.Instant;

import com.application.payment.controller.dto.Dto;
import com.application.payment.controller.dto.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse implements Dto {

	private String paymentId;
	private String status;
	private PaymentMode paymentMode;
	private Double amount;
	private Instant paymentDate;
	private Long orderId;
}