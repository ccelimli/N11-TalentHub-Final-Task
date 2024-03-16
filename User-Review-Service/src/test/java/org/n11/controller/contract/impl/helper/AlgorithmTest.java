package org.n11.controller.contract.impl.helper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@ExtendWith(MockitoExtension.class)
public class AlgorithmTest {
    @Test
    void shouldCalculateDistance() {
        double userLatitude = 40.7128;
        double userLongitude = -74.0060;
        double restaurantLatitude = 34.0522;
        double restaurantLongitude = -118.2437;

        double expectedDistance = 3935.746254609722;

        double actualDistance = Algorithm.calculateDistance(userLatitude, userLongitude, restaurantLatitude, restaurantLongitude);

        assertEquals(expectedDistance, actualDistance, 0.01);


    }

    @Test
    void shouldRecommendRestaurants() {
        UserDTO user = mock(UserDTO.class);
        when(user.latitude()).thenReturn(40.7128);
        when(user.longitude()).thenReturn(-74.0060);

        RestaurantDTO restaurant1 = mock(RestaurantDTO.class);
        when(restaurant1.latitude()).thenReturn(34.0522);
        when(restaurant1.longitude()).thenReturn(-118.2437);
        when(restaurant1.average()).thenReturn(4.5);

        RestaurantDTO restaurant2 = mock(RestaurantDTO.class);
        when(restaurant2.latitude()).thenReturn(37.7749);
        when(restaurant2.longitude()).thenReturn(-122.4194);
        when(restaurant2.average()).thenReturn(5.0);

        List<RestaurantDTO> restaurants = new ArrayList<>();
        restaurants.add(restaurant1);
        restaurants.add(restaurant2);

        List<RestaurantDTO> recommendedRestaurants = Algorithm.recommendRestaurants(user, 10000.0, restaurants);

        assertEquals(2, recommendedRestaurants.size());
        assertEquals(restaurant2, recommendedRestaurants.get(1));
    }
}
