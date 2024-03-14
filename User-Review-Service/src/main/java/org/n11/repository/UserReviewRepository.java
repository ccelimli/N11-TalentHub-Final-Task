package org.n11.repository;

import org.n11.entity.UserReview;
import org.n11.entity.dto.RestaurantDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@Repository
public interface UserReviewRepository extends JpaRepository<UserReview,Long> {
    @Query("select u from UserReview u where u.restaurantId=:restaurantId")
    List<UserReview> findByRestaurantId(@Param("restaurantId") String restaurantId);
}
