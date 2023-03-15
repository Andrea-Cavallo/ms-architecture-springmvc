package com.application.user.mapper;

import org.springframework.stereotype.Component;

import com.application.user.controller.dto.response.UserDTO;
import com.application.user.documents.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserMapper {

	public User toEntity(UserDTO dto) {
		return User.builder().email(dto.getEmail()).name(dto.getName()).userId(dto.getUserId()).build();

	}

	public UserDTO toDto(User entity) {
		return UserDTO.builder().email(entity.getEmail()).name(entity.getName()).userId(entity.getUserId()).build();

	}
}
