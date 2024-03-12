package org.n11.entity.dto;

import org.n11.entity.enums.Gender;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public record UserDTO(Long id,
                      String firstName,
                      String lastName,
                      LocalDate birthDate,
                      String email,
                      String phoneNumber,
                      String username,
                      Gender gender,
                      Double latitude,
                      Double longitude) {
}
