package org.n11.controller.contract.impl.helper;

import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.dto.UserDTO;

import java.util.*;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public class Algorithm {
    private static final double RATING_WEIGHT = 0.7;
    private static final double DISTANCE_WEIGHT = 0.3;

    public static List<RestaurantDTO> recommendRestaurants(UserDTO user, Double MAX_DISTANCE, List<RestaurantDTO> restaurants) {
        List<RestaurantDTO> filteredRestaurants = new ArrayList<>();
        for (RestaurantDTO restaurant : restaurants) {
            double distance = calculateDistance(user.latitude(), user.longitude(), restaurant.latitude(), restaurant.longitude());
            if (distance <= MAX_DISTANCE) {
                filteredRestaurants.add(restaurant);
            }
        }

        Collections.sort(filteredRestaurants, (r1, r2) -> {
            double score1 = r1.average() * RATING_WEIGHT + (10 - calculateDistance(user.latitude(), user.longitude(), r1.latitude(), r1.longitude())) * DISTANCE_WEIGHT;
            double score2 = r2.average() * RATING_WEIGHT + (10 - calculateDistance(user.latitude(), user.longitude(), r2.latitude(), r2.longitude())) * DISTANCE_WEIGHT;
            return Double.compare(score2, score1);
        });

        return filteredRestaurants.subList(0, Math.min(3, filteredRestaurants.size()));
    }

    private static double calculateDistance(double userLatitude, double userLongitude, double restaurantLatitude, double restaurantLongitude) {
        final int EARTH_RADIUS_KM = 6371;

        double latDistance = Math.toRadians(restaurantLatitude - userLatitude);
        double lonDistance = Math.toRadians(restaurantLongitude - userLongitude);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                   + Math.cos(Math.toRadians(userLatitude)) * Math.cos(Math.toRadians(restaurantLatitude))
                     * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}

