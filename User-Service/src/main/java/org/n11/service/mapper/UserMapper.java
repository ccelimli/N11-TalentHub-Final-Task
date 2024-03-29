package org.n11.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.n11.entity.User;
import org.n11.entity.dto.UserDTO;
import org.n11.entity.request.UserSaveRequest;
import org.n11.entity.request.UserUpdateRequest;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "phoneNumber", expression = "java(userSaveRequest.countryCode().getContext() + userSaveRequest.phoneNumber())")
    User convertToUser(UserSaveRequest userSaveRequest);

    UserDTO convertToDTO(User user);
    List<UserDTO> convertToDTOs(List<User> user);

    @Mapping(target = "phoneNumber", expression = "java(userUpdateRequest.countryCode().getContext() + userUpdateRequest.phoneNumber())")
    User updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
