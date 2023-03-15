package com.application.order.external.response;

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
public class ProductDto implements Dto {

	private String productName;
	private Long price;
	private Long quantity;
	private Long productId;
}
