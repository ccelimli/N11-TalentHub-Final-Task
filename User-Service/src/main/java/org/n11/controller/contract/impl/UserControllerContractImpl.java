package org.n11.controller.contract.impl;

import lombok.RequiredArgsConstructor;
import org.n11.controller.contract.UserControllerContract;
import org.n11.entity.dto.UserDTO;
import org.n11.entity.request.UserSaveRequest;
import org.n11.entity.request.UserUpdateRequest;
import org.n11.service.UserEntityService;
import org.n11.utilities.helper.BusinessRules;
import org.springframework.stereotype.Service;

import java.util.List;

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
        BusinessRules.controlEmail(userSaveRequest.email());
        return null;
    }

    @Override
    public UserDTO update(UserUpdateRequest userUpdateRequest) {
        return null;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
