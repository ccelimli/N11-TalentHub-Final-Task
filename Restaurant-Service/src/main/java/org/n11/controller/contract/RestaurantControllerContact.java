package org.n11.controller.contract;

import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.enums.Activity;
import org.n11.entity.request.RestaurantSaveRequest;
import org.n11.entity.request.RestaurantUpdateActivityRequest;
import org.n11.entity.request.RestaurantUpdateRequest;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public interface RestaurantControllerContact {
    RestaurantDTO save(RestaurantSaveRequest restaurantSaveRequest);

    String deleteRestaurant(String id);

    RestaurantDTO updateRestaurant(RestaurantUpdateRequest restaurantUpdateRequest);

    List<RestaurantDTO> findAllRestaurants();
    RestaurantDTO findById(String id);
    RestaurantDTO changeToActivity(RestaurantUpdateActivityRequest restaurantUpdateActivityRequest);
    List<RestaurantDTO> findByActivities(Activity activity);
}
