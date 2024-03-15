package org.n11.controller;

import org.n11.controller.contract.SuggestRestaurantControllerContract;
import org.n11.entity.dto.RestaurantDTO;
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
public class SuggestRestaurantController {
    private final SuggestRestaurantControllerContract suggestRestaurantControllerContract;

    public SuggestRestaurantController(SuggestRestaurantControllerContract suggestRestaurantControllerContract) {
        this.suggestRestaurantControllerContract = suggestRestaurantControllerContract;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> getAllRestaurants(){
        return ResponseEntity.ok(RestResponse.of(this.suggestRestaurantControllerContract.getAllRestaurants()));
    }
}
