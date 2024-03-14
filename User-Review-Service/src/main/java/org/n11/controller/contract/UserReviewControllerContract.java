package org.n11.controller.contract;

import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.dto.UserReviewDTO;
import org.n11.entity.request.UserReviewSaveRequest;
import org.n11.entity.request.UserReviewUpdateTextRequest;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public interface UserReviewControllerContract {
    UserReviewDTO save(UserReviewSaveRequest userReviewSaveRequest);
    UserReviewDTO findById(Long id);
    List<UserReviewDTO> findAll();
    String delete(Long id);
    UserReviewDTO updateByReviewText(UserReviewUpdateTextRequest userReviewUpdateTextRequest);
}
