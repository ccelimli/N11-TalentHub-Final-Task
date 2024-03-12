package org.n11.controller;

import org.n11.constant.Messages;
import org.n11.controller.contract.UserControllerContract;
import org.n11.entity.dto.UserDTO;
import org.n11.entity.request.UserSaveRequest;
import org.n11.entity.request.UserUpdateRequest;
import org.n11.utilities.general.entity.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserControllerContract userControllerContract;

    public UserController(UserControllerContract userControllerContract) {
        this.userControllerContract = userControllerContract;
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserDTO>> save(@RequestBody UserSaveRequest userSaveRequest){
        return ResponseEntity.ok(RestResponse.of(this.userControllerContract.save(userSaveRequest)));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<UserDTO>>> findAll(){
        return ResponseEntity.ok(RestResponse.of(this.userControllerContract.findAll()));
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<RestResponse<UserDTO>> active(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(this.userControllerContract.active(id)));
    }

    @PatchMapping("/deactive/{id}")
    public ResponseEntity<RestResponse<UserDTO>> deactive(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(this.userControllerContract.deactive(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserDTO>> findById(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(this.userControllerContract.getUserById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UserDTO>> update(@PathVariable Long id,@RequestBody UserUpdateRequest userUpdateRequest){
        return ResponseEntity.ok(RestResponse.of(this.userControllerContract.update(id,userUpdateRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<String>> delete(Long id){
        return ResponseEntity.ok(RestResponse.message(this.userControllerContract.delete(id)));
    }
}
