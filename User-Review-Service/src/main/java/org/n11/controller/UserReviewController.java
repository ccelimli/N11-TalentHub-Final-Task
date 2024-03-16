package org.n11.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.n11.controller.contract.UserReviewControllerContract;
import org.n11.entity.dto.UserDTO;
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

    @Operation(
            description = "New Create a User Review",
            summary = "Create",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Review Infos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserReviewSaveRequest.class
                            ),
                            examples = {
                                    @ExampleObject(
                                            name = "New User Review",
                                            summary = "New",
                                            description = "Complete request with all available fields for a new User Review",
                                            value ="{\"userId\":2,\n" +
                                                   " \"restaurantId\":\"string\",\n" +
                                                   " \"reviewText\":\"This is a review text\",\n" +
                                                   " \"rate\":\"1\",\n" +
                                                   " \"reviewDate\":\"2024-03-14\"}"
                                    )
                            }
                    )
            )
    )
    @PostMapping
    public ResponseEntity<RestResponse<UserReviewDTO>> save(@RequestBody UserReviewSaveRequest userReviewSaveRequest) {
        return ResponseEntity.ok(RestResponse.of(this.userReviewControllerContract.save(userReviewSaveRequest)));
    }

    @Operation(
            description = " Find with Id a User Review",
            summary = "Find By Id",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Review Id",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserReviewDTO>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(RestResponse.of(this.userReviewControllerContract.findById(id)));
    }

    @Operation(
            description = " Find All User Reviews",
            summary = "Find All",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Review Id",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    @GetMapping
    public ResponseEntity<RestResponse<List<UserReviewDTO>>> findAll() {
        return ResponseEntity.ok(RestResponse.of(this.userReviewControllerContract.findAll()));
    }

    @Operation(
            description = " Delete By Id a User Reviews",
            summary = "Delete",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Review Delete By Id",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(RestResponse.of(this.userReviewControllerContract.delete(id)));
    }

    @Operation(
            description = "Update a recorded User Review",
            summary = "Update",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Review Infos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserReviewUpdateTextRequest.class
                            ),
                            examples = {
                                    @ExampleObject(
                                            name = "Update User Review",
                                            summary = "Update",
                                            description = "Update the information of a recorded User Review",
                                            value ="{\"id\":2,\n" +
                                                   " \"reviewText\":\"This is a review text\",\n" +
                                                   " \"rate\":\"ONE\",\n"
                                    )
                            }
                    )
            )
    )
    @PatchMapping("/{id}")
    public ResponseEntity<RestResponse<UserReviewDTO>> updateText(@PathVariable Long id,@RequestBody UserReviewUpdateTextRequest userReviewUpdateTextRequest) {
       return ResponseEntity.ok(RestResponse.of(this.userReviewControllerContract.updateByReviewText(userReviewUpdateTextRequest)));
    }

    @Operation(
            description = " Find with Restaurant Id in User Reviews",
            summary = "Find By Restaurant Id",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurant Id",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    @GetMapping("/with-restaurants/{id}")
    public ResponseEntity<RestResponse<List<UserReviewDTO>>> findByRestaurant(@PathVariable String id){
        return ResponseEntity.ok(RestResponse.of(this.userReviewControllerContract.findByResturantId(id)));
    }
}
