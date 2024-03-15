package org.n11.controller.contract.impl.helper;

import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.dto.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public class Algorithm {
    private static final double RATING_WEIGHT = 0.7;
    private static final double DISTANCE_WEIGHT = 0.3;

    public static List<RestaurantDTO> recommendRestaurants(UserDTO user,Double maxDistance, List<RestaurantDTO> restaurants) {
        Map<RestaurantDTO, Map<RestaurantDTO, Double>> graph = new HashMap<>();
        for (RestaurantDTO restaurantDTO : restaurants) {
            Map<RestaurantDTO, Double> edges = new HashMap<>();
            for (RestaurantDTO other : restaurants) {
                if (restaurantDTO != other) {
                    double distance = calculateDistance(user.latitude(), user.longitude(), restaurantDTO.latitude(), restaurantDTO.longitude());
                    if (distance <= maxDistance) {
                        double weight = restaurantDTO.average() * RATING_WEIGHT + (10 - distance) * DISTANCE_WEIGHT;
                        edges.put(other, weight);
                    }
                }
            }
            graph.put(restaurantDTO, edges);
        }

        return new ArrayList<>();
    }

    private static double calculateDistance(double userLatitude, double userLongitude, double restaurantLatitude, double restaurantLongitude) {
        final int EARTH_RADIUS_KM = 6371; // Dünyanın yarıçapı kilometre cinsinden

        double latDistance = Math.toRadians(restaurantLatitude - userLatitude);
        double lonDistance = Math.toRadians(restaurantLongitude - userLongitude);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                   + Math.cos(Math.toRadians(userLatitude)) * Math.cos(Math.toRadians(restaurantLatitude))
                     * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}
