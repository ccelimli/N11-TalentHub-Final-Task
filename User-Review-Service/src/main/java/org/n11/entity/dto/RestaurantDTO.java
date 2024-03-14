package org.n11.entity.dto;

import java.time.LocalTime;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public record RestaurantDTO(
        String name,
        String phoneNumber,
        String address,
        String website,
        LocalTime openingTime,
        LocalTime closingTime,
        Double latitude,
        Double longitude,
        String status,
        Double averageDTO
) {
}
