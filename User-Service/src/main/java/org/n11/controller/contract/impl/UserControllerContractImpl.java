package org.n11.controller.contract.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.n11.constant.ErrorMessages;
import org.n11.constant.Messages;
import org.n11.controller.contract.UserControllerContract;
import org.n11.entity.User;
import org.n11.entity.dto.UserDTO;
import org.n11.entity.enums.Status;
import org.n11.entity.request.UserSaveRequest;
import org.n11.entity.request.UserUpdateRequest;
import org.n11.service.UserEntityService;
import org.n11.service.mapper.UserMapper;
import org.n11.utilities.exceptions.ItemNotFoundException;
import org.n11.utilities.helper.BusinessRules.RegularExpression;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        RegularExpression.controlFirstNameAndLastName(userSaveRequest.firstName(), userSaveRequest.lastName());

        User user = UserMapper.INSTANCE.convertToUser(userSaveRequest);
        this.userEntityService.save(user);
        return UserMapper.INSTANCE.convertToDTO(user);
    }

    @Transactional
    @Override
    public UserDTO update(UserUpdateRequest userUpdateRequest) {
        User user = this.userEntityService.findByIdWithControl(userUpdateRequest.id());
        if (user == null) {
            throw new ItemNotFoundException(ErrorMessages.NOT_FOUND_USER);
        }
        RegularExpression.controlEmail(userUpdateRequest.email());
        RegularExpression.controlPhoneNumber(userUpdateRequest.phoneNumber());
        RegularExpression.controlFirstNameAndLastName(userUpdateRequest.firstName(), userUpdateRequest.lastName());

        UserMapper.INSTANCE.updateUser(user, userUpdateRequest);
        this.userEntityService.save(user);
        return UserMapper.INSTANCE.convertToDTO(user);


    }

    @Override
    public UserDTO findById(Long id) {
        User user = this.userEntityService.findByIdWithControl(id);
        if (user == null) {
            throw new ItemNotFoundException(ErrorMessages.NOT_FOUND_USER);
        }
        return UserMapper.INSTANCE.convertToDTO(user);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> userList = this.userEntityService.findAll()
                .stream()
                .filter(user -> user.getStatus() == Status.ACTIVE)
                .toList();

        return UserMapper.INSTANCE.convertToDTOs(userList);
    }

    @Override
    public List<UserDTO> findAllByDeactive() {
        List<User> userList = this.userEntityService.findAll()
                .stream()
                .filter(user -> user.getStatus() == Status.DEACTIVE)
                .toList();

        return UserMapper.INSTANCE.convertToDTOs(userList);
    }

    @Override
    public UserDTO findByIdInDeactive(Long id) {
        User user = this.userEntityService.findByIdWithControl(id);
        if (user == null) {
            throw new ItemNotFoundException(ErrorMessages.NOT_FOUND_USER);
        }
        User userOptional = this.userEntityService.findAll()
                .stream()
                .filter(u -> u.getStatus() == Status.DEACTIVE && u.getId() == id)
                .findFirst().orElseThrow();


        return UserMapper.INSTANCE.convertToDTO(userOptional);
    }

    @Override
    public String delete(Long id) {
        User user = this.userEntityService.findByIdWithControl(id);
        if (user == null) {
            throw new ItemNotFoundException(ErrorMessages.NOT_FOUND_USER);
        }
        this.userEntityService.delete(user);
        return Messages.USER_DELETED.getMessage();
    }

    @Override
    public UserDTO active(Long id) {
        User user = this.userEntityService.findByIdWithControl(id);
        if (user == null) {
            throw new ItemNotFoundException(ErrorMessages.NOT_FOUND_USER);
        }
        this.userEntityService.changeStatusToActive(user.getId());
        return UserMapper.INSTANCE.convertToDTO(user);
    }

    @Override
    public UserDTO deactive(Long id) {
        User user = this.userEntityService.findByIdWithControl(id);
        if (user == null) {
            throw new ItemNotFoundException(ErrorMessages.NOT_FOUND_USER);
        }
        this.userEntityService.changeStatusToDeactive(user.getId());
        return UserMapper.INSTANCE.convertToDTO(user);
    }
}
