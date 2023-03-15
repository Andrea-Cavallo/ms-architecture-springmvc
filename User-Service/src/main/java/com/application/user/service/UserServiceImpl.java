package com.application.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.user.controller.dto.request.UserRequest;
import com.application.user.controller.dto.response.UserDTO;
import com.application.user.controller.exception.InvalidEmailException;
import com.application.user.controller.exception.UserNotFoundException;
import com.application.user.documents.User;
import com.application.user.mapper.UserMapper;
import com.application.user.repo.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private UserMapper userMapper;

	@Autowired
	private UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public UserDTO createUser(UserRequest userRequest, String uuid) {
		String email = userRequest.getEmail();
		log.info("Dentro il service di Create user, String uuid is " + uuid);
		if (Boolean.FALSE.equals(isEmailValid(email))) {
			throw new InvalidEmailException();
		}
		User u = fromUserRequest(userRequest, uuid);
		User saved = userRepository.save(u);
		return userMapper.toDto(saved);
	}

	public List<UserDTO> createAListOfUser(List<UserRequest> userRequestList, List<String> uuidList) {
		List<UserDTO> listOfUser = new ArrayList<>();
		log.info("Dentro il service di MultiCreate user");

		for (int i = 0; i < userRequestList.size(); i++) {
			UserRequest userRequest = userRequestList.get(i);
			String uuid = uuidList.get(i);
			UserDTO userDTO = this.createUser(userRequest, uuid);
			listOfUser.add(userDTO);
		}
		return listOfUser;
	}

	@Override
	public UserDTO updateUser(UserRequest user, String transactionId) {
		var name = user.getName();
		var email = user.getEmail();
		if (Boolean.FALSE.equals(isEmailValid(email))) {
			throw new InvalidEmailException();
		}
		log.info("Dentro il service di Update user");
		var userToSave = userRepository.findFirstByTransactionId(transactionId)
				.orElseThrow(() -> new UserNotFoundException(transactionId));
		userToSave.setName(name);
		userToSave.setEmail(email);
		userRepository.save(userToSave);
		return userMapper.toDto(userToSave);
	}

	@Override
	public Boolean deleteUser(String transactionId) {
		log.info("Dentro il service di Delete user");

		User user = userRepository.findFirstByTransactionId(transactionId)
				.orElseThrow(() -> new UserNotFoundException(transactionId));
		userRepository.deleteUserByTransactionId(user.getTransactionId());
		return true;
	}

	@Override
	public UserDTO getUserByTransactionId(String transactionID) {
		log.info("Dentro il service di get user");

		return userRepository.findFirstByTransactionId(transactionID).map(u -> userMapper.toDto(u))
				.orElseThrow(() -> new UserNotFoundException(transactionID));

	}

	private User fromUserRequest(UserRequest userRequest, String uuid) {
		return User.builder().name(userRequest.getName()).transactionId(uuid).email(userRequest.getEmail()).build();
	}

	private Boolean isEmailValid(String email) {
		log.info("Check del formato dell'Email");

		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		return email.matches(regex);
	}

}
