package org.n11.entity.dto;

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
        String status,
        Double average,
        Double latitude,
        Double longitude
) {
    public RestaurantDTO withAverage(Double average) {
        return new RestaurantDTO( id,name, phoneNumber, address, website, openingTime,closingTime,status, average,latitude,longitude);
    }
}
