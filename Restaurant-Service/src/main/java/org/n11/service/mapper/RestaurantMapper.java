package org.n11.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.n11.entity.Restaurant;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.request.RestaurantSaveRequest;
import org.n11.entity.request.RestaurantUpdateRequest;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Çağatay Çelimli
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {
    RestaurantMapper INSTANCE= Mappers.getMapper(RestaurantMapper.class);

    @Mapping(target="activity", constant = "ACTIVE")
    Restaurant convertToEntity(RestaurantSaveRequest restaurantSaveRequest);

    RestaurantDTO convertToDTO(Restaurant restaurant);

    List<RestaurantDTO> convertToDTOS(List<Restaurant> restaurant);

    Restaurant convertTOUpdate(Restaurant restaurant, RestaurantUpdateRequest restaurantUpdateRequest);
}
