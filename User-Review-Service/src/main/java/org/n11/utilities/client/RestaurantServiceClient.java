package org.n11.utilities.client;

import org.n11.entity.dto.RestaurantDTO;
import org.n11.utilities.general.entity.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@FeignClient(value = "restaurant", url = "http://localhost:8082/api/v1/restaurants")
public interface RestaurantServiceClient {
    @GetMapping("/{restaurantId}")
    ResponseEntity<RestResponse<RestaurantDTO>> getRestaurantById(@PathVariable("restaurantId") String restaurantId);
    @GetMapping
    ResponseEntity<RestResponse<List<RestaurantDTO>>> getAllRestaurants();
}
