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
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@RestController
@RequestMapping("/api/v1/recommend-restaurants")
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

//    @Operation(
//            description = "Recommend 3 restaurants to User",
//            summary = "Recommend",
//            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
//                    description = "Restaurants Infos",
//                    content = @Content(
//                            mediaType = "application/json"
//                    )
//            )
//    )
    @PostMapping("/{id}")
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> recommendRestaurants(@PathVariable Long id, @RequestBody RecommendRestaurantRequest recommendRestaurantRequest){
        return ResponseEntity.ok(RestResponse.of(this.recommendRestaurantControllerContract.recommendRestaurants(recommendRestaurantRequest)));
    }
}
