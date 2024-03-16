package org.n11.entity.request;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalTime;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public record RestaurantUpdateRequest(
        @NotNull String id,
        @NotNull String name,
        @NotNull String phoneNumber,
        @NotNull String address,
        @NotNull String website,
        @NotNull LocalTime openingTime,
        @NotNull LocalTime closingTime,
        @NotNull Double latitude,
        @NotNull Double longitude) {
}
