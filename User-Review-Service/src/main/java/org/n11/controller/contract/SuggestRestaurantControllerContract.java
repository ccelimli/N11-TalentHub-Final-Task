package org.n11.controller.contract;

import org.n11.entity.dto.RestaurantDTO;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public interface SuggestRestaurantControllerContract {
    List<RestaurantDTO> suggestRestaurants(Long userId);
    List<RestaurantDTO> getAllRestaurants();
}
