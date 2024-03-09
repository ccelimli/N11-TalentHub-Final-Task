package org.n11.entity.request;

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
        String username,
        String password,
        Gender gender
){
}
