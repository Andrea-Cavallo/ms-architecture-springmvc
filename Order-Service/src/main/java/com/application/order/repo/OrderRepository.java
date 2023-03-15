package com.application.order.repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.order.documents.Order;

public interface OrderRepository extends MongoRepository<Order, ObjectId> {

}
