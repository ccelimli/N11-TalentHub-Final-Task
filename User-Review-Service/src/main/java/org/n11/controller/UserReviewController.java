package org.n11.controller;

import org.n11.controller.contract.UserReviewControllerContract;
import org.n11.entity.dto.UserReviewDTO;
import org.n11.entity.request.UserReviewSaveRequest;
import org.n11.entity.request.UserReviewUpdateTextRequest;
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
@RequestMapping("/api/v1/user-reviews")
public class UserReviewController {
    private final UserReviewControllerContract userReviewControllerContract;

    public UserReviewController(UserReviewControllerContract userReviewControllerContract) {
        this.userReviewControllerContract = userReviewControllerContract;
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserReviewDTO>> save(@RequestBody UserReviewSaveRequest userReviewSaveRequest) {
        return ResponseEntity.ok(RestResponse.of(this.userReviewControllerContract.save(userReviewSaveRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserReviewDTO>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(RestResponse.of(this.userReviewControllerContract.findById(id)));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<UserReviewDTO>>> findAll() {
        return ResponseEntity.ok(RestResponse.of(this.userReviewControllerContract.findAll()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(RestResponse.of(this.userReviewControllerContract.delete(id)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RestResponse<UserReviewDTO>> updateText(UserReviewUpdateTextRequest userReviewUpdateTextRequest) {
       return ResponseEntity.ok(RestResponse.of(this.userReviewControllerContract.updateByReviewText(userReviewUpdateTextRequest)));
    }

    @GetMapping("/with-restaurants/{id}")
    public ResponseEntity<RestResponse<List<UserReviewDTO>>> findByRestaurant(String id){
        return ResponseEntity.ok(RestResponse.of(this.userReviewControllerContract.findByResturantId(id)));
    }
}
