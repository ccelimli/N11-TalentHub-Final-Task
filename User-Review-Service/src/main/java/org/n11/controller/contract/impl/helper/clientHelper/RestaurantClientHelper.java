package org.n11.controller.contract.impl.helper.clientHelper;

import lombok.RequiredArgsConstructor;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.dto.UserDTO;
import org.n11.utilities.client.RestaurantServiceClient;
import org.n11.utilities.client.UserServiceClient;
import org.n11.utilities.general.entity.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@Service
@RequiredArgsConstructor
public class RestaurantClientHelper {
    private final  RestaurantServiceClient restaurantServiceClient;

    public  RestaurantDTO getRestaurantDetails(String restaurantId) {
        ResponseEntity<RestResponse<RestaurantDTO>> restaurantServiceResponse = restaurantServiceClient.getRestaurantById(restaurantId);
        return restaurantServiceResponse.getBody().getData();
    }

    public List<RestaurantDTO> getAllRestaurant(){
        ResponseEntity<RestResponse<List<RestaurantDTO>>> restaurantServiceList= restaurantServiceClient.getAllRestaurants();
        return restaurantServiceList.getBody().getData();
    }
}
