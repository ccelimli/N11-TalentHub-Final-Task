package org.n11.entity.request;

import java.time.LocalTime;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public record RestaurantSaveRequest(String name,
                                    String phoneNumber,
                                    String address,
                                    String website,
                                    LocalTime openingTime,
                                    LocalTime closingTime,
                                    Double latitude,
                                    Double longitude) {
}
