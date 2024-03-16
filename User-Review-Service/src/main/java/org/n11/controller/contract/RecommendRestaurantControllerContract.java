package org.n11.controller.contract;

import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.request.RecommendRestaurantRequest;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public interface RecommendRestaurantControllerContract {
    List<RestaurantDTO> suggestRestaurants(RecommendRestaurantRequest recommendRestaurantRequest);
    List<RestaurantDTO> getAllRestaurants();
}
