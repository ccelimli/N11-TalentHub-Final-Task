package org.n11.entity.dto;

import org.n11.entity.enums.Activity;
import org.n11.entity.enums.Status;

import java.time.LocalTime;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public record RestaurantDTO(
        String id,
        String name,
        String phoneNumber,
        String address,
        String website,
        LocalTime openingTime,
        LocalTime closingTime,
        Double latitude,
        Double longitude,
        Status status,
        Activity activity
        ) {
}
