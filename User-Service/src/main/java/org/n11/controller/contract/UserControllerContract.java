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
    UserDTO update( UserUpdateRequest userUpdateRequest);
    UserDTO findById(Long id);
    List<UserDTO> findAll();
    List<UserDTO> findAllByDeactive();
    UserDTO findByIdInDeactive(Long id);
    String delete(Long id);
    UserDTO active(Long id);
    UserDTO deactive(Long id);
}
