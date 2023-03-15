package com.application.order.controller.dto.request;

import java.time.Instant;

import com.application.order.external.request.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OrderRequest {

	private String productId;
	private Long quantity;
	private Instant orderDate;
	private String orderStatus;
	private long totalAmount;
	private PaymentMode paymentMode;
}
