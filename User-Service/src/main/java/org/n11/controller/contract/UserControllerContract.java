package org.n11.controller.contract;

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
public interface UserControllerContract {
    UserDTO save(UserSaveRequest userSaveRequest);
    UserDTO update(Long id, UserUpdateRequest userUpdateRequest);
    UserDTO getUserById(Long id);
    List<UserDTO> findAll();
    void delete(Long id);
    void active(Long id);
    void deactive(Long id);
}
