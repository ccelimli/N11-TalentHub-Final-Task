package org.n11.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.n11.controller.contract.RestaurantControllerContact;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.request.RestaurantSaveRequest;
import org.n11.entity.request.RestaurantUpdateActivityRequest;
import org.n11.entity.request.RestaurantUpdateRequest;
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
@RequestMapping("api/v1/restaurants")
@Tag(name = "Restaurant Controller", description = "Restaurant Management")
public class RestaurantController {
    private final RestaurantControllerContact restaurantControllerContact;

    public RestaurantController(RestaurantControllerContact restaurantControllerContact) {
        this.restaurantControllerContact = restaurantControllerContact;
    }


    @Operation(
            description = "New Create Restaurant",
            summary = "Create A New Restaurant",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurant Infos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = RestaurantSaveRequest.class
                            ),
                            examples = {
                                    @ExampleObject(
                                            name = "New Restaurant",
                                            summary = "New",
                                            description = "Complete request with all available fields for a new restaurant",
                                            value = "{\"name\":\"Tonia Mesant\",\n" +
                                                    " \"phoneNumber\":\"211-590-5898\",\n" +
                                                    " \"address\":\"10 West Junction\",\n" +
                                                    " \"website\":\"http://vistaprint.com/\",\n" +
                                                    " \"openingTime\":\"10:20\",\n" +
                                                    " \"closingTime\":\"02:30\",\n" +
                                                    " \"latitude\":30.7058907,\n" +
                                                    " \"longitude\":76.785812}"
                                    )
                            }
                    )
            )
)
    @PostMapping
    public ResponseEntity<RestResponse<RestaurantDTO>> save(@RequestBody RestaurantSaveRequest restaurantSaveRequest) {
        return ResponseEntity.ok(RestResponse.of(this.restaurantControllerContact.save(restaurantSaveRequest)));
    }

    @Operation(
            description = " Delete By Id A Restaurant",
            summary = "Delete A Restaurants",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Id",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<String>> delete(@PathVariable String id) {
        return ResponseEntity.ok(RestResponse.of(this.restaurantControllerContact.deleteRestaurant(id)));
    }

    @Operation(
            description = "New Create Restaurant",
            summary = "Create A New Restaurant",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurant Infos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = RestaurantSaveRequest.class
                            ),
                            examples = {
                                    @ExampleObject(
                                            name = "Restaurant",
                                            summary = "Update",
                                            description = "Update the information of a recorded restaurant",
                                            value = "{" +
                                                    "\"id\":\"string\",\n" +
                                                    "\"name\":\"Josephina\",\n" +
                                                    " \"phoneNumber\":\"+905555555555\",\n" +
                                                    " \"address\":\"49 Northwestern Way\",\n" +
                                                    " \"website\":\"www.website.com\",\n" +
                                                    " \"openingTime\":\"08:30\",\n" +
                                                    " \"closingTime\": \"23:30\",\n" +
                                                    " \"latitude\":37.6854422,\n" +
                                                    " \"longitude\":48.3412639\n" +
                                                    "}"
                                    )
                            }
                    )
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantDTO>> update(@PathVariable String id, @RequestBody RestaurantUpdateRequest restaurantUpdateRequest) {
        return ResponseEntity.ok(RestResponse.of(this.restaurantControllerContact.updateRestaurant(restaurantUpdateRequest)));
    }

    @Operation(
            description = "Find All Recorded Restaurants",
            summary = "Find All",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurants Infos",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    @GetMapping
    public ResponseEntity<RestResponse<Iterable<RestaurantDTO>>> findAll() {
        return ResponseEntity.ok(RestResponse.of(this.restaurantControllerContact.findAllRestaurants()));
    }

    @Operation(
            description = " Find with Id a Restaurant",
            summary = "Find By Id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurant Id",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantDTO>> findById(@PathVariable String id) {
        return ResponseEntity.ok(RestResponse.of(this.restaurantControllerContact.findById(id)));
    }

    @Operation(
            description = "Change Activity of Recorded Restaurant",
            summary = "Change To Activity",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurant Id and Restaurant Activity",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = RestaurantUpdateActivityRequest.class
                            ),
                            examples = {
                                    @ExampleObject(
                                            name = "User",
                                            summary = "Change Activity",
                                            description = "Change a recorded Restaurant's activity",
                                            value = "{\n"
                                                    + "  \"id\": \"11\"\n"
                                                    + "  \"activity\": \"ACTIVE\"\n"
                                                    + "}"
                                    )
                            }
                    )
            )
    )
    @PatchMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantDTO>> changeToActivity(@PathVariable String id, @RequestBody RestaurantUpdateActivityRequest restaurantUpdateActivityRequest) {
        return ResponseEntity.ok(RestResponse.of(this.restaurantControllerContact.changeToActivity(restaurantUpdateActivityRequest)));
    }

    @Operation(
            description = " Find Restaurants containing the specified string expression in their name",
            summary = "Find By Name",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurant Name",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    @GetMapping("/with-restaurants/{name}")
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> findByName(@PathVariable String name){
        return ResponseEntity.ok(RestResponse.of(this.restaurantControllerContact.findByName(name)));
    }
}
