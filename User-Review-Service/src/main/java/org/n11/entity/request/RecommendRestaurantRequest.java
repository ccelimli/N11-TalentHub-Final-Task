package org.n11.entity.request;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public record RecommendRestaurantRequest(
        Long userId,
        Double maxDistance) {

}
