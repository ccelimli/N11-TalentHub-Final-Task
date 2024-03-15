package org.n11.entity.request;

import java.sql.Timestamp;
import java.time.LocalTime;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public record RestaurantUpdateRequest(
        String id,
        String name,
        String phoneNumber,
        String address,
        String website,
        LocalTime openingTime,
        LocalTime closingTime,
        Double latitude,
        Double longitude) {
}
