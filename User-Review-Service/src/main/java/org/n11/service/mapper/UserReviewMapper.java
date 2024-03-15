package org.n11.service.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.n11.entity.UserReview;
import org.n11.entity.dto.RestaurantDTO;
import org.n11.entity.dto.UserDTO;
import org.n11.entity.dto.UserReviewDTO;
import org.n11.entity.enums.Rate;
import org.n11.entity.request.UserReviewSaveRequest;
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
    @Mapping(target = "rate", source = "userReviewSaveRequest.rate", qualifiedByName = "fromIntToRate")
    UserReview convertToEntity(UserReviewSaveRequest userReviewSaveRequest);

    @Mapping(target = "id", source = "userReview.id")
    @Mapping(target = "fullName", expression = "java(userDTO.firstName() + ' ' + userDTO.lastName())")
    @Mapping(target = "restaurantName", source = "restaurantDTO.name")
    @Mapping(target = "reviewText", source = "userReview.reviewText")
    @Mapping(target = "rate", source = "userReview.rate.value")
    @Mapping(target = "reviewDate", source = "userReview.reviewDate")
    UserReviewDTO convertToDTO(UserReview userReview, UserDTO userDTO, RestaurantDTO restaurantDTO);

    //List<UserReviewDTO> userReviewDTOs(List<UserReview> userReviewList, List<UserDTO> userDTOS, RestaurantDTO restaurantDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reviewText", source = "userReviewUpdateTextRequest.reviewText")
    @Mapping(target = "rate", source = "userReviewUpdateTextRequest.rate", qualifiedByName = "fromIntToRate")
   UserReview updateText(@MappingTarget UserReview userReview, UserReviewUpdateTextRequest userReviewUpdateTextRequest);

    @Named("fromIntToRate")
    default Rate fromIntToRate(Integer rate) {
        if (rate == null) {
            return null;
        }
        return Rate.fromValue(rate);
    }


}
