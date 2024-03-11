package org.n11.entity.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.n11.constant.CountryCode;
import org.n11.entity.enums.Gender;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public record UserSaveRequest(
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        LocalDate birthDate,
        @NotNull
        String email,
        @NotNull
        String phoneNumber,
        @NotNull
        CountryCode countryCode,
        @NotNull
        String username,
        @NotNull
        String password,
        @NotNull
        Gender gender,
        @NotNull
        @Min(-90)
        @Max(90)
        Double latitude,
        @NotNull
        @Min(-180)
        @Max(180)
        Double longitude
) {
}
