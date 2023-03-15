package com.application.product.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.application.product.controller.dto.request.ProductRequest;
import com.application.product.controller.dto.response.ProductDto;
import com.application.product.controller.exception.ProductNotFoundException;
import com.application.product.controller.exception.ProductQuantityException;
import com.application.product.controller.mapper.ProductMapper;
import com.application.product.documents.Product;
import com.application.product.repository.ProductRepository;
import com.application.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	private ProductMapper productMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 60)
	public ProductDto createProduct(ProductRequest productRequest) {
		UUID generateUUid = UUID.randomUUID();
		String productId = this.toString(generateUUid);
		productRequest.setProductId(productId);

		log.info("..Inside service of create product...");
		Product product = productMapper.toDocument(productRequest);
		Product saved = productRepository.save(product);
		return productMapper.toDto(saved);

	}

	@Override
	public ProductDto getProduct(ProductRequest productRequest) {
		log.info("..Inside service of get product...");
		Long price = productRequest.getPrice();
		String name = productRequest.getProductName();
		String productId = productRequest.getProductId();

		Product product = Product.builder().productName(name).price(price).productId(productId).build();

		Optional<Product> productOptional = productRepository.findOne(Example.of(product));

		if (productOptional.isPresent()) {
			return productMapper.toDto(productOptional.get());
		} else {
			throw new ProductNotFoundException(name);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 60)

	public ProductDto reduceQuantity(ProductRequest productRequest) {
		log.info("..Inside service of Reduce Quantity product...");
		Long quantityToRemove = productRequest.getQuantity();
		String productId = productRequest.getProductId();

		Product product = productRepository.findByProductId(productId)
				.orElseThrow(() -> new ProductNotFoundException(productId));

		Long productQuantity = product.getQuantity();
		Long newQuantity = productQuantity - quantityToRemove;

		if (productQuantity < quantityToRemove) {

			throw new ProductQuantityException(quantityToRemove);
		}

		product.setQuantity(newQuantity); // Update the quantity of the existing product

		Product saved = productRepository.save(product); // Save the updated product to the database

		return productMapper.toDto(saved);
	}

	public String toString(Object obj) {
		return obj != null ? obj.toString() : "";
	}

}
