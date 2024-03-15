package org.n11.service;

import org.n11.entity.Restaurant;
import org.n11.entity.enums.Activity;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */

public interface RestaurantEntityService {


    Restaurant save(Restaurant restaurant);

    String deleteRestaurant(Restaurant Restaurant);

    Restaurant updateRestaurant(Restaurant restaurant);

    Iterable<Restaurant> findAllRestaurants();
    Restaurant findById(String id);
    Restaurant changeToActivity(Restaurant restaurant);
    List<Restaurant> findByActivity(String name);

}
