package com.application.user.mapper;

import org.bson.json.JsonObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import com.application.user.controller.dto.request.UserRequest;
import com.application.user.controller.dto.response.UserDTO;
import com.application.user.documents.User;

@Mapper(componentModel = "spring")

public interface UserMethodMapperMapStruct {
   UserDTO toUserDto(User person);
   User toUser(UserDTO dto);
   User fromUserReq(UserRequest userRequest);


  
}