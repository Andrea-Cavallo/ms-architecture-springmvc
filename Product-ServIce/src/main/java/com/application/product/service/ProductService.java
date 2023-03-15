package com.application.product.service;

import com.application.product.controller.dto.request.ProductRequest;
import com.application.product.controller.dto.response.ProductDto;

public interface ProductService {

	ProductDto createProduct(ProductRequest productRequest);

	ProductDto getProduct(ProductRequest productRequest);

	ProductDto reduceQuantity(ProductRequest productRequest);

}
