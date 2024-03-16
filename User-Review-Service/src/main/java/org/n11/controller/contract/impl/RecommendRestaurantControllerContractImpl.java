package org.n11.controller.contract.impl;

import lombok.RequiredArgsConstructor;
import org.n11.controller.contract.RecommendRestaurantControllerContract;
import org.n11.controller.contract.UserReviewControllerContract;
import org.n11.controller.contract.impl.helper.Algorithm;
import org.n11.controller.contract.impl.helper.clientHelper.RestaurantClientHelper;
import org.n11.controller.contract.impl.helper.clientHelper.UserClientHelper;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.dto.UserDTO;
import org.n11.entity.dto.UserReviewDTO;
import org.n11.entity.request.RecommendRestaurantRequest;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@Service
@RequiredArgsConstructor
public class RecommendRestaurantControllerContractImpl implements RecommendRestaurantControllerContract {
    private final UserReviewControllerContract userReviewControllerContract;
    private final RestaurantClientHelper restaurantClientHelper;
    private final UserClientHelper userClientHelper;
    @Override
    public List<RestaurantDTO> recommendRestaurants(RecommendRestaurantRequest recommendRestaurantRequest) {
        List<RestaurantDTO> restaurantDTOS=this.restaurantClientHelper.getAllRestaurant();
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        nf.setMaximumFractionDigits(1);

        for (RestaurantDTO restaurantDTO : restaurantDTOS) {
            Double average = this.userReviewControllerContract.findByResturantId(restaurantDTO.id())
                    .stream()
                    .mapToDouble(UserReviewDTO::rate)
                    .average()
                    .orElse(0.0);
            average = Double.valueOf(nf.format(average));
            restaurantDTO = restaurantDTO.withAverage(average);

            restaurantDTOList.add(restaurantDTO);
        }
        UserDTO userDTO=userClientHelper.getUserDetails(recommendRestaurantRequest.userId());
        return Algorithm.recommendRestaurants(userDTO, recommendRestaurantRequest.maxDistance(),restaurantDTOList);
    }

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        List<RestaurantDTO> restaurantDTOList = this.restaurantClientHelper.getAllRestaurant();
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        nf.setMaximumFractionDigits(1);

        for (RestaurantDTO restaurantDTO : restaurantDTOList) {
            Double average = this.userReviewControllerContract.findByResturantId(restaurantDTO.id())
                    .stream()
                    .mapToDouble(UserReviewDTO::rate)
                    .average()
                    .orElse(0.0);
            average = Double.valueOf(nf.format(average));
            restaurantDTO = restaurantDTO.withAverage(average);

            restaurantDTOS.add(restaurantDTO);
        }
        return restaurantDTOS;
    }
}
