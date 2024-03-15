package org.n11.controller.contract.impl.helper.clientHelper;

import lombok.RequiredArgsConstructor;
import org.n11.entity.dto.UserDTO;
import org.n11.utilities.client.UserServiceClient;
import org.n11.utilities.general.entity.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@Service
@RequiredArgsConstructor
public class UserClientHelper {

    private final UserServiceClient userServiceClient;
    public UserDTO getUserDetails(Long userId) {
        ResponseEntity<RestResponse<UserDTO>> userServiceResponse = userServiceClient.getUserById(userId);
        return userServiceResponse.getBody().getData();
    }

}
