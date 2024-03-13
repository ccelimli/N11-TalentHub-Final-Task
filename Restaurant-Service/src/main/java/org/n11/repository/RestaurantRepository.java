package org.n11.repository;

import org.n11.entity.Restaurant;
import org.springframework.data.solr.repository.SolrCrudRepository;


/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
public interface RestaurantRepository extends SolrCrudRepository<Restaurant, String> {
}
