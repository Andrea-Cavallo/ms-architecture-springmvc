package com.application.payment.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.payment.documents.TransactionDetails;

public interface TransactionDetailsRepository extends MongoRepository<TransactionDetails, ObjectId> {

	TransactionDetails findByOrderId(long orderId);

}
