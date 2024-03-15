package org.n11.controller.contract.impl;

import lombok.RequiredArgsConstructor;
import org.n11.controller.contract.SuggestRestaurantControllerContract;
import org.n11.controller.contract.UserReviewControllerContract;
import org.n11.controller.contract.impl.helper.clientHelper.RestaurantClientHelper;
import org.n11.entity.UserReview;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.dto.UserReviewDTO;
import org.n11.service.UserReviewEntityService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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
public class SuggestRestaurantControllerContractImpl implements SuggestRestaurantControllerContract {
    private final UserReviewControllerContract userReviewControllerContract;
    private final RestaurantClientHelper restaurantClientHelper;
    @Override
    public List<RestaurantDTO> suggestRestaurants(Long userId) {
        return null;
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
                    .mapToDouble(userReview -> userReview.rate())
                    .average()
                    .orElse(0.0);
            // Average değerini virgülden sonra 1 basamak göstermek için NumberFormat kullan
            average = Double.valueOf(nf.format(average));
            restaurantDTO = restaurantDTO.withAverage(average);

            restaurantDTOS.add(restaurantDTO);
        }
        return restaurantDTOS;
    }
}
