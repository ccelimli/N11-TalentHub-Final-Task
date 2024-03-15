package org.n11.entity.request;

import org.n11.entity.enums.Rate;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public record UserReviewSaveRequest(
        Long userId,
        String restaurantId,
        String reviewText,
        Integer rate) {
}
