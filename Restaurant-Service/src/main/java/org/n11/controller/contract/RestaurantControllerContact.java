package org.n11.controller.contract;

import org.apache.catalina.User;
import org.n11.entity.Restaurant;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.request.RestaurantSaveRequest;
import org.n11.entity.request.RestaurantUpdateActivityRequest;
import org.n11.entity.request.RestaurantUpdateRequest;

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

    Iterable<RestaurantDTO> findAllRestaurants();
    RestaurantDTO findById(String id);
    RestaurantDTO changeToActivity(RestaurantUpdateActivityRequest restaurantUpdateActivityRequest);

    Iterable<RestaurantDTO> suggestRestaurant(User);
}
