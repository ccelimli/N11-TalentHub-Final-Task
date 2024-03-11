package org.n11.controller.contract.impl;

import lombok.RequiredArgsConstructor;
import org.n11.controller.contract.UserControllerContract;
import org.n11.entity.User;
import org.n11.entity.dto.UserDTO;
import org.n11.entity.enums.Status;
import org.n11.entity.request.UserSaveRequest;
import org.n11.entity.request.UserUpdateRequest;
import org.n11.service.UserEntityService;
import org.n11.service.mapper.UserMapper;
import org.n11.utilities.helper.BusinessRules.RegularExpression;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@Service
@RequiredArgsConstructor
public class UserControllerContractImpl implements UserControllerContract {

    private final UserEntityService userEntityService;

    @Override
    public UserDTO save(UserSaveRequest userSaveRequest) {
        RegularExpression.controlEmail(userSaveRequest.email());
        RegularExpression.controlPhoneNumber(userSaveRequest.phoneNumber());
        RegularExpression.controlFirstNameAndLastName(userSaveRequest.firstName(),userSaveRequest.lastName());

        User user = UserMapper.INSTANCE.convertToUser(userSaveRequest);
        this.userEntityService.save(user);
        return UserMapper.INSTANCE.convertToDTO(user);
    }

    @Override
    public UserDTO update(Long id,UserUpdateRequest userUpdateRequest) {
        RegularExpression.controlEmail(userUpdateRequest.email());
        RegularExpression.controlPhoneNumber(userUpdateRequest.phoneNumber());
        RegularExpression.controlFirstNameAndLastName(userUpdateRequest.firstName(),userUpdateRequest.lastName());

        User user=this.userEntityService.findByIdWithControl(userUpdateRequest.id());
        UserMapper.INSTANCE.updateUser(userUpdateRequest);
        return UserMapper.INSTANCE.convertToDTO(user);


    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> userList = this.userEntityService.findAll()
                .stream()
                .filter(user -> user.getStatus() == Status.ACTIVE)
                .collect(Collectors.toList());

        return UserMapper.INSTANCE.convertToDTOs(userList);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void active(Long id) {

    }

    @Override
    public void deactive(Long id) {

    }
}
