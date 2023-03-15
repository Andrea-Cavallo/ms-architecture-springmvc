package com.application.user.mapper;

import org.bson.json.JsonObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.application.user.controller.dto.response.UserDTO;
import com.application.user.documents.User;

@Mapper
public interface UserMethodMapperMapStruct {
   UserDTO toUserDto(User person);
   User toUser(UserDTO dto);

   @Mapping(source = "name", target = "name")
   @Mapping(source = "userId", target = "userId")
   @Mapping(source = "email", target = "email")
  // @Mapping(source = "age", target = "age", qualifiedByName = "yearsToMonths")

   User mapJsonToUser(JsonObject json);

   @Named("yearsToMonths")
   default int yearsToMonths(int years) {
       return years * 12;
   }
}