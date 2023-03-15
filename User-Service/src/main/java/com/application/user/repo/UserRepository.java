package com.application.user.repo;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.user.documents.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {

	Optional<User> findFirstByUserId(String userId);

	void deleteUserByUserId(String userId);

}