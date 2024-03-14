package org.n11.utilities.client;

import org.n11.entity.dto.UserDTO;
import org.n11.utilities.general.entity.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@FeignClient(value = "user",url = "http://localhost:8081/api/v1/users")
public interface UserServiceClient {
    @GetMapping("/{userId}")
    ResponseEntity<RestResponse<UserDTO>> getUserById(@PathVariable("userId") Long userId);

}
