package org.n11.controller.contract.impl;

import org.n11.controller.contract.UserReviewControllerContract;
import org.n11.entity.dto.UserReviewDTO;
import org.n11.entity.request.UserReviewSaveRequest;
import org.n11.entity.request.UserReviewUpdateRequest;
import org.n11.entity.request.UserReviewUpdateTextRequest;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public class UserReviewControllerContractImpl implements UserReviewControllerContract{
    @Override
    public UserReviewDTO save(UserReviewSaveRequest userReviewSaveRequest) {
        return null;
    }

    @Override
    public UserReviewDTO update(UserReviewUpdateRequest userReviewUpdateRequest) {
        return null;
    }

    @Override
    public UserReviewDTO findById(Long id) {
        return null;
    }

    @Override
    public List<UserReviewDTO> findAll() {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    @Override
    public UserReviewDTO updateByReviewText(UserReviewUpdateTextRequest userReviewUpdateTextRequest) {
        return null;
    }
}
