package org.n11.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.n11.controller.contract.RecommendRestaurantControllerContract;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.request.RecommendRestaurantRequest;
import org.n11.utilities.general.entity.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@RestController
@RequestMapping("/api/v1/user-reviews/restaurants")
public class RecommendRestaurantController {
    private final RecommendRestaurantControllerContract recommendRestaurantControllerContract;

    public RecommendRestaurantController(RecommendRestaurantControllerContract recommendRestaurantControllerContract) {
        this.recommendRestaurantControllerContract = recommendRestaurantControllerContract;
    }

    @Operation(
            description = " Find All Restaurants with Average Ratings from Restaurants Service",
            summary = "Find All",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restaurants",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    )
    @GetMapping
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> getAllRestaurants(){
        return ResponseEntity.ok(RestResponse.of(this.recommendRestaurantControllerContract.getAllRestaurants()));
    }

    @Operation(
            description = "Recommend 3 restaurants to User",
            summary = "Recommend",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Restausrants Infos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = RecommendRestaurantRequest.class
                            ),
                            examples = {
                                    @ExampleObject(
                                            name = "Update User Review",
                                            summary = "Update",
                                            description = "Recommend 3 restaurants according to the proximity value in km determined by the user.",
                                            value = "{\n" +
                                                    "  \"userId\": 1,\n" +
                                                    "  \"maxDistance\": 5000\n" +
                                                    "}"
                                    )
                            }
                    )
            )
    )
    @GetMapping("/with-suggest-restaurant")
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> suggestRestaurants(RecommendRestaurantRequest recommendRestaurantRequest){
        return ResponseEntity.ok(RestResponse.of(this.recommendRestaurantControllerContract.recommendRestaurants(recommendRestaurantRequest)));
    }
}
