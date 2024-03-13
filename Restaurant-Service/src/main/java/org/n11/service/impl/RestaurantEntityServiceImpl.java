package org.n11.service.impl;

import lombok.RequiredArgsConstructor;
import org.n11.constant.Messages;
import org.n11.entity.Restaurant;
import org.n11.repository.RestaurantRepository;
import org.n11.service.RestaurantEntityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@Service
@RequiredArgsConstructor
public class RestaurantEntityServiceImpl implements RestaurantEntityService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return this.restaurantRepository.save(restaurant);
    }

    @Override
    public String deleteRestaurant(Restaurant restaurant) {
        restaurantRepository.delete(restaurant);
        return Messages.SUCCESSFUL_DELETE.getContext();
    }

    @Transactional
    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        return this.restaurantRepository.save(restaurant);
    }

    @Override
    public Iterable<Restaurant> findAllRestaurants() {
        return this.restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(String id) {
        return this.restaurantRepository.findById(id).orElseThrow();
    }

    @Override
    public Restaurant changeToActivity(Restaurant restaurant) {
        return this.restaurantRepository.save(restaurant);
    }
}
