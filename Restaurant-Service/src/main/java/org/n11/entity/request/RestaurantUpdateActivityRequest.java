package org.n11.entity.request;

import org.n11.entity.enums.Activity;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public record RestaurantUpdateActivityRequest (
        String id,
        Activity activity

){
}
