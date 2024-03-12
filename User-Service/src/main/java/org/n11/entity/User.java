package org.n11.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.n11.entity.enums.Gender;
import org.n11.entity.enums.Status;
import org.n11.utilities.general.entity.BaseEntity;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    @SequenceGenerator(name = "User", sequenceName = "USER_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User")
    @Id
    private Long id;

    @NotBlank
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Past
    @Column(name = "birth_date", nullable = false)
    @NotBlank
    private LocalDate birthDate;

    @NotBlank
    @Column(name = "email", nullable = false, length = 300)
    private String email;

    @NotBlank
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotBlank
    @Column (name = "username", nullable = false, length = 50)
    private String username;

    @NotBlank
    @Column(name = "password", nullable = false, length = 28)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    @NotBlank
    private Gender gender;

    @NotBlank
    @Column(name = "latitude", nullable = false)
    private double latitude;

    @NotBlank
    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @NotBlank
    private Status status;


}
