package org.n11.entity.request;

import org.n11.entity.enums.Activity;

import javax.validation.constraints.NotNull;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public record RestaurantUpdateActivityRequest (
        @NotNull String id,
        @NotNull Activity activity
){
}
