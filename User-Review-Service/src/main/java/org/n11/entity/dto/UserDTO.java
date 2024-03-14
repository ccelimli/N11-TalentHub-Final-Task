package org.n11.entity.dto;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        Double latitude,
        Double longitude
) {
}
