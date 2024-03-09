package org.n11.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.n11.entity.User;
import org.n11.entity.dto.UserDTO;
import org.n11.entity.request.UserSaveRequest;

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
    User convertToUser(UserSaveRequest userSaveRequest);

    UserDTO convertToDTO(User user);
    List<UserDTO> convertToDTOs(List<User> user);
}
