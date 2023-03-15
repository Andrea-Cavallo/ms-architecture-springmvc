package com.application.user.service;

import java.util.List;

import com.application.user.controller.dto.request.UserRequest;
import com.application.user.controller.dto.response.UserDTO;

public interface UserService {

	UserDTO createUser(UserRequest user, String uuid);

	UserDTO getUserByTransactionId(String transactionId);

	List<UserDTO> createAListOfUser(List<UserRequest> userRequestList, List<String> uuidList);

	UserDTO updateUser(UserRequest user, String transactionId);

	Boolean deleteUser(String transactionId);

}
