package org.n11.controller.contract.impl;

import lombok.RequiredArgsConstructor;
import org.n11.controller.contract.SuggestRestaurantControllerContract;
import org.n11.entity.UserReview;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.dto.SuggestRestaurantDTO;
import org.n11.service.UserReviewEntityService;
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
public class SuggestRestaurantControllerContractImpl implements SuggestRestaurantControllerContract {
    private final UserReviewEntityService userReviewEntityService;
    @Override
    public List<SuggestRestaurantDTO> suggestRestaurants(Long userId) {
        return null;
    }
}
