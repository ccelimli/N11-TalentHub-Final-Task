package org.n11.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name="User Controller", description = "User Management")
public class UserController {
    private final UserControllerContract userControllerContract;

    public UserController(UserControllerContract userControllerContract) {
        this.userControllerContract = userControllerContract;
    }

    @PostMapping
    @Operation(
            description = "New Create User",
            summary = "Create A New User",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Infos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserSaveRequest.class
                            ),
                            examples = {
                                    @ExampleObject(
                                            name = "New User",
                                            summary = "New",
                                            description = "Complete request with all available fields for a new user",
                                            value = "{\n"
                                                    + "  \"firstName\": \"John\",\n"
                                                    + "  \"lastName\": \"Doe\",\n"
                                                    + "  \"birthDate\": \"1990-05-15\",\n"
                                                    + "  \"email\": \"john.doe@example.com\",\n"
                                                    + "  \"phoneNumber\": \"1234567890\",\n"
                                                    + "  \"countryCode\": \"US\",\n"
                                                    + "  \"username\": \"johndoe\",\n"
                                                    + "  \"password\": \"password123\",\n"
                                                    + "  \"gender\": \"MALE\",\n"
                                                    + "  \"latitude\": 40.7128,\n"
                                                    + "  \"longitude\": -74.0060\n"
                                                    + "}"
                                    )
                            }
                    )
            )
    )
    public ResponseEntity<RestResponse<UserDTO>> save(@Valid @RequestBody UserSaveRequest userSaveRequest){
        return ResponseEntity.ok(RestResponse.of(this.userControllerContract.save(userSaveRequest)));
    }

    @Operation(
            description = "Find All Registered User",
            summary = "Find All",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Infos",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    @GetMapping
    public ResponseEntity<RestResponse<List<UserDTO>>> findAll(){
        return ResponseEntity.ok(RestResponse.of(this.userControllerContract.findAll()));
    }

    @Operation(
            description = "Change Status To Active From Registered Users",
            summary = "Change To Active",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserDTO.class
                            )
                    )
            )
    )
    @PatchMapping("/active/{id}")
    public ResponseEntity<RestResponse<UserDTO>> active(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(this.userControllerContract.active(id)));
    }

    @Operation(
            description = "Change Status To Deactive From Registered Users",
            summary = "Change To Deactive",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserUpdateRequest.class
                            ),
                            examples = {
                                    @ExampleObject(
                                            name = "User",
                                            summary = "Deactive",
                                            description = "Change a registered user's status to Deactive",
                                            value = "{\n"
                                                    + "  \"id\": \"11\"\n"
                                                    + "}"
                                    )
                            }
                    )
            )
    )
    @PatchMapping("/deactive/{id}")
    public ResponseEntity<RestResponse<UserDTO>> deactive(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(this.userControllerContract.deactive(id)));
    }

    @Operation(
            description = " Find with Id a User",
            summary = "Find By Id",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Id",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserDTO>> findById(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(this.userControllerContract.findById(id)));
    }

    @Operation(
            description = "A Update User",
            summary = "Update A User",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Infos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserUpdateRequest.class
                            ),
                            examples = {
                                    @ExampleObject(
                                            name = "User",
                                            summary = "Update",
                                            description = "Update the information of a registered user",
                                            value = "{\n"
                                                    + "  \"id\": \"11\",\n"
                                                    + "  \"firstName\": \"John\",\n"
                                                    + "  \"lastName\": \"Doe\",\n"
                                                    + "  \"birthDate\": \"1990-05-15\",\n"
                                                    + "  \"email\": \"john.doe@example.com\",\n"
                                                    + "  \"phoneNumber\": \"1234567890\",\n"
                                                    + "  \"countryCode\": \"US\",\n"
                                                    + "  \"username\": \"johndoe\",\n"
                                                    + "  \"password\": \"password123\",\n"
                                                    + "  \"gender\": \"MALE\",\n"
                                                    + "  \"latitude\": 40.7128,\n"
                                                    + "  \"longitude\": -74.0060\n"
                                                    + "}"
                                    )
                            }
                    )
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UserDTO>> update(@PathVariable Long id,@Valid @RequestBody UserUpdateRequest userUpdateRequest){
        return ResponseEntity.ok(RestResponse.of(this.userControllerContract.update(userUpdateRequest)));
    }

    @Operation(
            description = " Delete By Id A User",
            summary = "Delete A User",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Id",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<String>> delete(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.message(this.userControllerContract.delete(id)));
    }

    @Operation(
            description ="List registered users whose Status feature is Deactivated",
            summary = "Find All Deactives",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Infos",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    @GetMapping("/with-deactives")
    public ResponseEntity<RestResponse<List<UserDTO>>> findAllByDeactive(){
        return ResponseEntity.ok(RestResponse.of(this.userControllerContract.findAllByDeactive()));
    }

    @Operation(
            description ="Fetch a specific piece from users whose status feature is Deactivated.",
            summary = "Find User By Id In Deactives",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Infos",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    @GetMapping("/with-deactives/{id}")
    public ResponseEntity<RestResponse<UserDTO>> findByIdInDeactive(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(this.userControllerContract.findByIdInDeactive(id)));
    }
}
