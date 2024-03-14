package org.n11.entity.request;

import org.n11.entity.enums.Rate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public record UserReviewUpdateTextRequest(
        Long id,
        Rate rate,
        String reviewText) {
}
