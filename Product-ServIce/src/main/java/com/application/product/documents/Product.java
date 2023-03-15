package com.application.product.documents;

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
@Setter
@EqualsAndHashCode
@Document(collection = "product")
@NoArgsConstructor
public class Product {

	private String productId;

	private String productName;
	private Long price;
	private Long quantity;

}
