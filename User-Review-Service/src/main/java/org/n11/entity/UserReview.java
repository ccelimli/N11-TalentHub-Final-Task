package org.n11.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.n11.entity.enums.Rate;
import org.n11.utilities.general.entity.BaseEntity;

import java.time.LocalDate;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@Entity
@Table(name = "user-reviews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReview extends BaseEntity {
    @SequenceGenerator(name = "UserReview", sequenceName = "USER_REVIEW_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserReview")
    @Id
    private Long id;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "restaurant_id", nullable = false)
    private String restaurantId;
    @Column(name = "review_text", nullable = false,length = 1000)
    private String reviewText;
    @Column(name = "rate", nullable = false)
    @Enumerated(EnumType.STRING)
    private Rate rate;
    @Column(name = "review_date", nullable = false)
    private LocalDate reviewDate;
}
