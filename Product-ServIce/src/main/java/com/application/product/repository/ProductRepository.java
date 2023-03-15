package com.application.product.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.product.documents.Product;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {
	Optional<Product> findByProductId(String productId);

}
