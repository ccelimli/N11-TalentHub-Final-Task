package org.n11.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.n11.entity.UserReview;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.dto.UserDTO;
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
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserReviewMapper {
    UserReviewMapper INSTANCE = Mappers.getMapper(UserReviewMapper.class);

    @Mapping(target = "reviewDate", expression = "java(LocalDate.now())")
    UserReview convertToEntity(UserReviewSaveRequest userReviewSaveRequest);

    @Mapping(target = "id", source = "userReview.id")
    @Mapping(target = "userFullName", expression = "java(userDTO.firstName + ' ' + userDTO.lastName)")
    @Mapping(target = "restaurantName", source = "restaurantDTO.name")
    @Mapping(target = "reviewText", source = "userReview.reviewText")
    @Mapping(target = "rate", source = "userReview.rate")
    @Mapping(target = "reviewDate", source = "userReview.reviewDate")
    UserReviewDTO convertToDTO(UserReview userReview, UserDTO userDTO, RestaurantDTO restaurantDTO);

    List<UserReviewDTO> userReviewDTOs(List<UserReview> userReviewList, List<UserDTO> userDTOS, RestaurantDTO restaurantDTO);

    UserReview updateEntity(UserReview userReview, UserReviewUpdateRequest userReviewUpdateRequest);

    UserReview updateText(UserReview userReview, UserReviewUpdateTextRequest userReviewUpdateTextRequest);
}
