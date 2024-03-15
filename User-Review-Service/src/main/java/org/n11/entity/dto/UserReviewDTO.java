package org.n11.entity.dto;

import org.n11.entity.enums.Rate;
import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public record UserReviewDTO(
        Long id,
        String fullName,
        String restaurantName,
        String reviewText,
        Integer rate,
        LocalDate reviewDate
) {
}
