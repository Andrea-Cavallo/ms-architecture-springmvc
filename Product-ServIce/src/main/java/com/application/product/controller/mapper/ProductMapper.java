package com.application.product.controller.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.application.product.controller.dto.request.ProductRequest;
import com.application.product.controller.dto.response.ProductDto;
import com.application.product.documents.Product;

@Component
public class ProductMapper {

	public ProductDto toDto(Product product) {
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(product, dto);
		return dto;

	}

	public Product toDocument(ProductRequest request) {
		Product product = new Product();
		BeanUtils.copyProperties(request, product);
		return product;

	}

}
