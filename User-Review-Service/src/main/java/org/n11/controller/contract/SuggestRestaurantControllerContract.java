package org.n11.controller.contract;

import org.n11.entity.dto.SuggestRestaurantDTO;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public interface SuggestRestaurantControllerContract {
    List<SuggestRestaurantDTO> suggestRestaurants(Long userId);
}
