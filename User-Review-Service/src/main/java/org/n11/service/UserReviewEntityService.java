package org.n11.service;

import org.n11.entity.UserReview;
import org.n11.repository.UserReviewRepository;
import org.n11.utilities.general.service.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@Service
public class UserReviewEntityService extends BaseEntityService<UserReview, UserReviewRepository> {
    protected UserReviewEntityService(UserReviewRepository repository) {
        super(repository);
    }

    public List<UserReview> findByRestaurantIdUserReviews(String restarurantId){
        return this.getRepository().findByRestaurantId(restarurantId);
    }
}
