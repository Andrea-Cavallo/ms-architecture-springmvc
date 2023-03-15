package com.application.user.controller.dto.response;

import com.application.user.controller.dto.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString

public class UserDTO implements Dto {

	private String name;
	private String email;
	private String userId;

}
