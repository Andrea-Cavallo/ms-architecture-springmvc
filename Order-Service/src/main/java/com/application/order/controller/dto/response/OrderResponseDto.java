package com.application.order.controller.dto.response;

import java.time.Instant;

import com.application.order.controller.dto.Dto;

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
public class OrderResponseDto implements Dto {

	private Long productId;
	private Long quantity;
	private Instant orderDate;
	private String orderStatus;
	private Long amount;
	private ProductDetails productDetails;

	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	@Getter
	@Setter
	@EqualsAndHashCode
	@ToString
	public static class ProductDetails implements Dto {

		private String productName;
		private Long price;
		private Long quantity;
		private Long productId;
	}

}
