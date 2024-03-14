package org.n11.entity.dto;

import java.time.LocalTime;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public record SuggestRestaurantDTO(
        String name,
        String phoneNumber,
        String address,
        String website,
        LocalTime openingTime,
        LocalTime closingTime,
        String status,
        Double averageDTO
) {
}
