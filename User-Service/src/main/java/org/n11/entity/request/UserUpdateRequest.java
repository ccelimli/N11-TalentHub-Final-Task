package org.n11.entity.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import org.n11.constant.CountryCode;
import org.n11.entity.enums.Gender;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public record UserUpdateRequest(
        Long id,
        String firstName,
        String lastName,
        LocalDate birthDate,
        String email,
        CountryCode countryCode,
        String phoneNumber,
        String username,
        String password,
        Gender gender,
        Double latitude,
        Double longitude
) {
}
