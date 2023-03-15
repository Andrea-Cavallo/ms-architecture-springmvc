package com.application.order.documents;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
@Document(collection = "order")
@NoArgsConstructor
@Setter
public class Order {
	private String productId;
	private Long quantity;
	private Instant orderDate;
	private String orderStatus;
	private Long amount;
}
