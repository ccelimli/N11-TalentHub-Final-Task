package org.n11.repository;

import org.n11.entity.Restaurant;
import org.n11.entity.enums.Activity;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;


/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public interface RestaurantRepository extends SolrCrudRepository<Restaurant, String> {
    @Query
    List<Restaurant> findByActivity(Activity activity);
}
